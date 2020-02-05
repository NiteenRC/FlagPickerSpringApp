package com.nc.flagpicker.service;

import java.util.List;

import com.nc.flagpicker.model.Continent;
import com.nc.flagpicker.model.Country;

/**
 * @author Niteen Chougula
 * @version 2.0
 * @since 2020-02-05
 */
public interface ICountryService {
	Country findByName(String name);

	List<Country> findByContinent(Continent continent);
}
