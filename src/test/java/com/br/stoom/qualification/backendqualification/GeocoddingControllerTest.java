package com.br.stoom.qualification.backendqualification;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.br.stoom.backendqualification.BackendQualificationApplication;
import com.br.stoom.backendqualification.controller.GeocodingController;
import com.br.stoom.backendqualification.dto.GeocodingDto;

@SpringBootTest(classes = BackendQualificationApplication.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class GeocoddingControllerTest {

	@Autowired
	private GeocodingController geocodingController;

	private GeocodingDto geocodingDto;
	private long id;

	@Before
	public void setUp() {
		geocodingDto = new GeocodingDto();
		geocodingDto.setStreetName("Zuneide Aparecida Marin");
		geocodingDto.setNumber(43);
		geocodingDto.setNeighbourhood("Jardim Santa Genebra II (Barão Geraldo)");
		geocodingDto.setCity("Campinas");
		geocodingDto.setState("SP");
		geocodingDto.setCountry("Brasil");
		geocodingDto.setZipcode("13084-780");

		id = 1l;
	}

	@Test
	public void deve_armazenar_geocoding() {
		ResponseEntity<?> response = geocodingController.post(geocodingDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void deve_recuperar_geocoding() {
		ResponseEntity<?> response = geocodingController.get(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void deve_atualizar_geocoding() {
		ResponseEntity<?> response = geocodingController.put(id, new GeocodingDto("Dom Afonso Henrique"));
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void deve_remover_geocoding() {
		ResponseEntity<?> response = geocodingController.delete(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
