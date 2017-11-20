package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostAddressDto {
	private Long id;
	private String name;
	private String index;
	private Long countryId;
	private Long  regionId;
	private String locality;
	private String street;
	private String houseNumber;
	private String buildingNumber;
	private String apartment;
	private String officeNumber;
	private LocalDate startDate;
	private LocalDate endDate;
	private Long businessPartnerId;
	private Long legalAddress;
	private Long actualAddress;
}
