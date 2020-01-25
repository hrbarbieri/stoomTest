package com.br.stoom.backendqualification.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.br.stoom.backendqualification.controller.exception.GeocodingException;
import com.br.stoom.backendqualification.dto.GeocodingDto;
import com.br.stoom.backendqualification.dto.convert.GeocodingDtoConverter;
import com.br.stoom.backendqualification.modal.Geocoding;
import com.br.stoom.backendqualification.repository.GeocodingRepository;
import com.br.stoom.backendqualification.utils.Translator;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;

@Service
public class GeocodingService {

	private static final String API_KEY = "AIzaSyBm3xh9oZP1ksMWcMzVaZQevWlrtb8tIgc";
	
	@Autowired
	private GeocodingRepository geocodingRepository;
	
	
	public Optional<Geocoding> findGeocoding(long id) {
		return geocodingRepository.findById(id);
	}
	
	
	public long createGeocoding(GeocodingDto geocodingDto) {
		
		if(StringUtils.isEmpty(geocodingDto.getLatitude())
				|| StringUtils.isEmpty(geocodingDto.getLongitude())) {
			
			 Geometry geometry = this.findGeocoding(geocodingDto.getCompoundAddress());
			 
			 if(geometry != null) {
				 geocodingDto.setLatitude(String.valueOf(geometry.location.lat));
				 geocodingDto.setLongitude(String.valueOf(geometry.location.lng));
			 }
		}
		
		Geocoding entity = new GeocodingDtoConverter().convert(geocodingDto);
		return geocodingRepository.save(entity).getId();
	}
	
	
	public void deleteGeocoding(long id) {
		
		Optional<Geocoding> optGeocoding = geocodingRepository.findById(id);
		
		if(!optGeocoding.isPresent()) {
			throw new GeocodingException(
					Translator.getText("geocoding.error.notExist", 
							new String[] {String.valueOf(id)}));
		}
		geocodingRepository.deleteById(id);
	}
	

	public void updateGeocoding(GeocodingDto geocodingDto) {
		
		Optional<Geocoding> optGeocoding = geocodingRepository.findById(geocodingDto.getId());
		
		if(!optGeocoding.isPresent()) {
			throw new GeocodingException(
					Translator.getText("geocoding.error.notExist", 
							new String[] {String.valueOf(geocodingDto.getId())}));
		}
		
		Geocoding geocoding = new GeocodingDtoConverter().convert(geocodingDto, optGeocoding.get());
		geocodingRepository.save(geocoding);
		
	}
	
	
	/**
	 * Recupera Latitude/Logitude
	 * 
	 * @param address
	 * @return
	 */
	private Geometry findGeocoding(String address) {
		
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(API_KEY).build();
		
		GeocodingResult[] results = null;
		try {
			results = GeocodingApi.geocode(context, address).await();
			
		} catch (ApiException e) {
			throw new GeocodingException(Translator.getText("geocoding.error.google", new String[] {address}));
		} catch (InterruptedException e) {
			throw new GeocodingException(e.getMessage());
		} catch (IOException e) {
			throw new GeocodingException(e.getMessage());
		}
		
		/*
		 * Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 * System.out.println(gson.toJson(results[0]));
		 */
		
		return results[0].geometry;
	}
	
	
	
}
