package com.nc.flagpicker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.flagpicker.exception.ErrorMessage;
import com.nc.flagpicker.exception.ResourceNotFoundException;
import com.nc.flagpicker.model.Continent;
import com.nc.flagpicker.repository.ContinentRepository;

/**
 * @author Niteen Chougula
 * @version 2.0
 * @since 2020-02-05
 */
@Service
public class ContinentService implements IContinentService {

	@Autowired
	ContinentRepository continentRepository;

	@Override
	public List<Continent> findAll() {
		List<Continent> continents = continentRepository.findAll();
		if (continents.isEmpty()) {
			throw new ResourceNotFoundException(ErrorMessage.CONTINENTS_NOT_FOUND);
		}
		return continents;
	}

	@Override
	public boolean isContinenPresent(String continentName) {
		return continentRepository.existsByContinentIgnoreCase(continentName);
	}

	@Override
	public Continent findByContinentName(String continentName) {
		Continent continent = continentRepository.findByContinentIgnoreCase(continentName);
		if (continent == null) {
			throw new ResourceNotFoundException(ErrorMessage.CONTINENT_NOT_FOUND);
		}
		return continent;
	}
}
