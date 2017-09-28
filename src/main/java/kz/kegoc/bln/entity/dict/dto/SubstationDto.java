package kz.kegoc.bln.entity.dict.dto;

import lombok.Data;

@Data
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
