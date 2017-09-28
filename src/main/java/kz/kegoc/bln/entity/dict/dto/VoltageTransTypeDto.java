package kz.kegoc.bln.entity.dict.dto;

import lombok.Data;

@Data
public class VoltageTransTypeDto {
	private Long id;
	private String code;
	private String name;
	private String manufacturer;
	private Double ratedVoltage1;
	private Double ratedVoltage2;
	private Double accuracyClass;
	private Double minVoltage;
	private Double maxVoltage;
}
