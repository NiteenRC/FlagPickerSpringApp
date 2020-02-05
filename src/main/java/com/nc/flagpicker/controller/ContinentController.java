package com.nc.flagpicker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nc.flagpicker.model.Continent;
import com.nc.flagpicker.model.Country;
import com.nc.flagpicker.service.IContinentService;
import com.nc.flagpicker.service.ICountryService;

/**
 * @author Niteen Chougula
 * @version 2.0
 * @since 2020-02-05
 */
@RestController
public class ContinentController {

	@Autowired
	IContinentService iContinentService;

	@Autowired
	ICountryService iCountryService;

	@GetMapping("/world")
	public List<Continent> findAllContinentAndCountries() {
		return iContinentService.findAll();
	}

	@GetMapping("/flag/{countryName}")
	public String findFlagByCountryName(@PathVariable String countryName) {
		return iCountryService.findByName(countryName).getFlag();

	}

	@GetMapping("/continent/{continentName}")
	public List<Country> findCountriesByContinentName(@PathVariable String continentName) {
		Continent continent = iContinentService.findByContinentName(continentName);
		return iCountryService.findByContinent(continent);
	}
}
