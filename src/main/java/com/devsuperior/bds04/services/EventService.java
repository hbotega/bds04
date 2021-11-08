package com.devsuperior.bds04.services;

import java.io.Serializable;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private EventRepository repository;
	
	@Autowired
	private CityRepository cityRepo;
	

	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
		Event entity = repository.getOne(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new EventDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new com.devsuperior.bds04.services.exceptions.ResourceNotFoundException("Id not found" + id);
		}
		
	}
	
	private void copyDtoToEntity(EventDTO dto, Event entity) {
		
		City city = cityRepo.getOne(dto.getCityId());
		
		
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
		entity.setCity(city);
		}
}
	



