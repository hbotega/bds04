package com.devsuperior.bds04.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**" };//caminhos para fazer login e acessar o H2 por isso são publicos
	
	private static final String[] CLIENT_GET = {"/events/**", "/cities/**"};
	
	

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//Libera o H2
		if(Arrays.asList(env.getActiveProfiles()).contains("test")){
			http.headers().frameOptions().disable();
		}
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.antMatchers(HttpMethod.GET, CLIENT_GET).permitAll()
		.antMatchers(HttpMethod.POST, CLIENT_GET).hasAnyRole("ADMIN")
		.anyRequest().hasAnyRole("ADMIN");
		
	}
	
	

}
