package com.br.stoom.backendqualification.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GeocodingDto {

	private long id;
	
	@NotBlank
	private String streetName;
	
	@NotNull
	private Integer number;
	
	private String complement;
	
	@NotBlank
	private String neighbourhood;
	
	@NotBlank(message="city \u00e9 obrigat\u00f3rio(a)")
	private String city;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String country;
	
	@NotBlank
	private String zipcode;
	
	private String latitude;
	
	private String longitude;
	
	
	public GeocodingDto(@NotBlank String streetName) {
		this.streetName = streetName;
	}

	public GeocodingDto() {
	}

	public String getCompoundAddress() {
		
		StringBuffer sb = new StringBuffer();
		sb.append(streetName.concat(",+"));
		sb.append(number.toString().concat("+"));
		sb.append(city.concat("/"));
		sb.append(state);
		
		return sb.toString().replace(" ", "+");
	}
	
	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	@Override
	public String toString() {
		return "GeocodingDto [streetName=" + streetName + ", number=" + number + ", complement=" + complement
				+ ", neighbourhood=" + neighbourhood + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", zipcode=" + zipcode + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeocodingDto other = (GeocodingDto) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}
	
	

	
}
