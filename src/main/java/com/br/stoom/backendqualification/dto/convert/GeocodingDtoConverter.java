package com.br.stoom.backendqualification.dto.convert;

import org.springframework.util.StringUtils;

import com.br.stoom.backendqualification.dto.GeocodingDto;
import com.br.stoom.backendqualification.modal.Geocoding;

public class GeocodingDtoConverter implements Converter<GeocodingDto, Geocoding>{

	@Override
	public Geocoding convert(GeocodingDto in) {
		
		Geocoding out = new Geocoding();
		
		out.setId(in.getId());
		out.setStreetName(in.getStreetName());
		out.setNumber(in.getNumber());
		out.setComplement(in.getComplement());
		out.setNeighbourhood(in.getNeighbourhood());
		out.setCity(in.getCity());
		out.setState(in.getState());
		out.setCountry(in.getCountry());
		out.setZipcode(in.getZipcode());
		out.setLatitude(in.getLatitude());
		out.setLongitude(in.getLongitude());
		
		return out;
	}
	
	
	@Override
	public Geocoding convert(GeocodingDto in, Geocoding out) {
		
		out.setId(in.getId());
		
		if(!StringUtils.isEmpty(in.getStreetName())) {
			out.setStreetName(in.getStreetName());
		}
			
		if(in.getNumber() != null) {
			out.setNumber(in.getNumber());
		}
		
		if(!StringUtils.isEmpty(in.getComplement())) {
			out.setComplement(in.getComplement());
		}
		
		if(!StringUtils.isEmpty(in.getNeighbourhood())) {
			out.setNeighbourhood(in.getNeighbourhood());
		}
		
		if(!StringUtils.isEmpty(in.getCity())) {
			out.setCity(in.getCity());
		}
		
		if(!StringUtils.isEmpty(in.getState())) {
			out.setState(in.getState());
		}
		
		if(!StringUtils.isEmpty(in.getCountry())) {
			out.setCountry(in.getCountry());
		}
		
		if(!StringUtils.isEmpty(in.getZipcode())) {
			out.setZipcode(in.getZipcode());
		}
		
		if(!StringUtils.isEmpty(in.getLatitude())) {
			out.setLatitude(in.getLatitude());
		}
		
		if(!StringUtils.isEmpty(in.getLongitude())) {
			out.setLongitude(in.getLongitude());
		}
		
		return out;
	}


}
