package com.devsuperior.bds04.controllers;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;

@RestController
@RequestMapping(value="/events")
public class EventController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EventService service;
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EventDTO> update(@Valid @PathVariable Long id, @RequestBody EventDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

}
