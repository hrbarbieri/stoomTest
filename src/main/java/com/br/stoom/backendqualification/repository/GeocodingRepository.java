package com.br.stoom.backendqualification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.stoom.backendqualification.modal.Geocoding;

public interface GeocodingRepository extends JpaRepository<Geocoding, Long>{

}
