package com.nc.flagpicker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.nc.flagpicker.model.Continent;
import com.nc.flagpicker.model.Country;

/**
 * @author Niteen Chougula
 * @version 2.0
 * @since 2020-02-05
 */
@RepositoryRestResource
public interface CountryRepository extends JpaRepository<Country, Integer> {
	
	Country findByName(String name);
	
	List<Country> findByContinent(Continent continent);

}
