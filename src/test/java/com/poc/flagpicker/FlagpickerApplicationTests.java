package com.poc.flagpicker;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nc.flagpicker.model.Continent;
import com.nc.flagpicker.model.Country;
import com.nc.flagpicker.service.IContinentService;
import com.nc.flagpicker.service.ICountryService;

@SpringBootTest
@RunWith(SpringRunner.class)
class FlagpickerApplicationTests {

	@Autowired
	private IContinentService iContinentService;

	@Autowired
	private ICountryService iCountryService;

	@Test
	public void testFetchAll() throws IOException {
		List<Continent> continents = iContinentService.findAll();
		List<Country> countries = continents.stream().map(continent -> continent.getCountries()).flatMap(List::stream)
				.collect(Collectors.toList());
		assertTrue(!continents.isEmpty());
		assertTrue(countries != null);
		assertEquals(5, continents.size());
	}

	@Test
	public void testIsContinenPresent() throws IOException {
		boolean continentName1 = !iContinentService.isContinenPresent("Africa");
		assertEquals(true, continentName1);

		boolean continentName2 = !iContinentService.isContinenPresent("India");
		assertEquals(false, continentName2);
	}

	@Test
	public void testFindByCountryName() throws IOException {
		Country countryName = iCountryService.findByName("Nigeria");
		assertEquals("NG", countryName);
	}
}
