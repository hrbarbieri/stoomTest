package com.br.stoom.backendqualification.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.stoom.backendqualification.dto.GeocodingDto;
import com.br.stoom.backendqualification.modal.Geocoding;
import com.br.stoom.backendqualification.services.GeocodingService;
import com.br.stoom.backendqualification.utils.Translator;

/**
 * Teste de Qualificação Backend STOOM
 * 
 * @author Herbert R. Barbieri
 *
 */
@RestController
@RequestMapping("/geocoding")
public class GeocodingController {

	@Autowired
	private GeocodingService geocodingService;
	
	@GetMapping("{id}")
	public ResponseEntity<?> get(@PathVariable long id) {
		
		Optional<Geocoding> optGeocoding = geocodingService.findGeocoding(id);
		
		if(optGeocoding.isPresent()) {
			return ResponseEntity.ok().body(optGeocoding.get());
		}
		return ResponseEntity.ok(Translator.getText("geocoding.error.notExist", 
				new String[] {String.valueOf(id)}));
	}
	

	@PostMapping
	public ResponseEntity<?> post(@Valid @RequestBody GeocodingDto geocodingDto) {
		long id = geocodingService.createGeocoding(geocodingDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(Translator.getText("geocoding.success.created", 
				new String[] {String.valueOf(id)}));
	}

	
	@PutMapping("{id}")
	public ResponseEntity<?> put(@Valid @PathVariable long id, @RequestBody GeocodingDto geocodingDto) {
		
		geocodingDto.setId(id);
		geocodingService.updateGeocoding(geocodingDto);
		
		return ResponseEntity.ok().body(Translator.getText("geocoding.success.update")); 
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		geocodingService.deleteGeocoding(id);
		return ResponseEntity.ok().body(Translator.getText("geocoding.success.delete"));
	}

}
