package com.nc.flagpicker.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.flagpicker.model.Continent;
import com.nc.flagpicker.repository.ContinentRepository;
import com.nc.flagpicker.repository.CountryRepository;
import com.nc.flagpicker.service.IContinentService;

/**
 * @author Niteen Chougula
 * @version 2.0
 * @since 2020-02-05
 */
@Configuration
public class InitializeData {

	@Autowired
	private ContinentRepository continentRepository;

	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private IContinentService continentService;

	@PostConstruct
	public void init() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:contients.json");
		InputStream inputStream = resource.getInputStream();
		TypeReference<List<Continent>> typeReference = new TypeReference<List<Continent>>() {
		};

		List<Continent> continents = objectMapper.readValue(inputStream, typeReference);
		continents.forEach(continent -> {
			boolean isConitnentPresent = continentService.isContinenPresent(continent.getContinent());
			if (isConitnentPresent) {
				continentRepository.save(continent);
				continent.getCountries().forEach(country -> {
					country.setContinent(continent);
					countryRepository.save(country);
				});
			}
		});
	}
}
