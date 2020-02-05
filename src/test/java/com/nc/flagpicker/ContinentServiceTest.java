package com.nc.flagpicker;

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
import com.nc.flagpicker.service.ContinentService;
import com.nc.flagpicker.service.ICountryService;

/**
 * @author Niteen Chougula
 * @version 2.0
 * @since 2020-02-05
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class ContinentServiceTest {

	@Autowired
	private ContinentService iContinentService;

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
		Country country = iCountryService.findByName("Nigeria");
		assertEquals("🇳🇬", country.getFlag());
	}
}
