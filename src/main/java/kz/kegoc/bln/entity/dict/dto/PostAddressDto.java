package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
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
	private String countryName;
	private Long  regionId;
	private String  regionName;
	private String locality;
	private String street;
	private String houseNumber;
	private String buildingNumber;
	private String apartment;
	private String officeNumber;
	private Lang lang;
}
