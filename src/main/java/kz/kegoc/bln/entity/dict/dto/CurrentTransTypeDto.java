package kz.kegoc.bln.entity.dict.dto;

import lombok.Data;

@Data
public class CurrentTransTypeDto {
	private Long id;
	private String code;
	private String name;
	private String manufacturer;
	private Double ratedCurrent1;
	private Double ratedCurrent2;
	private Double accuracyClass;
	private Double minCurrent;
	private Double maxCurrent;
}
