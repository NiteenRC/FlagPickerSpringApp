package com.nc.flagpicker.service;

import java.util.List;

import com.nc.flagpicker.model.Continent;

/**
 * @author Niteen Chougula
 * @version 2.0
 * @since 2020-02-05
 */
public interface IContinentService {
	List<Continent> findAll();

	boolean isContinenPresent(String continentName);
	
	Continent findByContinentName(String continentName);

}
