package com.nc.flagpicker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.flagpicker.exception.ErrorMessage;
import com.nc.flagpicker.exception.ResourceNotFoundException;
import com.nc.flagpicker.model.Continent;
import com.nc.flagpicker.model.Country;
import com.nc.flagpicker.repository.CountryRepository;

/**
 * @author Niteen Chougula
 * @version 2.0
 * @since 2020-02-05
 */
@Service
public class CountryService implements ICountryService {

	@Autowired
	CountryRepository countryRepository;

	@Override
	public Country findByName(String countryName) {
		Country country = countryRepository.findByName(countryName);
		if (country == null) {
			throw new ResourceNotFoundException(ErrorMessage.COUNTRY_NOT_FOUND);
		}
		return country;
	}

	@Override
	public List<Country> findByContinent(Continent continent) {
		List<Country> countries = countryRepository.findByContinent(continent);
		if (countries == null) {
			throw new ResourceNotFoundException(ErrorMessage.COUNTRIES_NOT_FOUND);
		}
		return countries;
	}
}
