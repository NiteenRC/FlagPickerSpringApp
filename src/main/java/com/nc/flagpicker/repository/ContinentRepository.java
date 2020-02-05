package com.nc.flagpicker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.nc.flagpicker.model.Continent;

/**
 * @author Niteen Chougula
 * @version 2.0
 * @since 2020-02-05
 */
@RepositoryRestResource
public interface ContinentRepository extends JpaRepository<Continent, Integer> {

	Continent findByContinent(String continent);
}
