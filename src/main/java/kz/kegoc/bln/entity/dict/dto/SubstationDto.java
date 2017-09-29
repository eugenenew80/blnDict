package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubstationDto {
	private Long id;
	private String code;
	private String name;
	private String shortName;
	private Long substationTypeId;
	private String substationTypeName;	
	private Double voltageClass;
	private String address;
}
