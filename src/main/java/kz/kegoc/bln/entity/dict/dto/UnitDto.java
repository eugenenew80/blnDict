package kz.kegoc.bln.entity.dict.dto;

import lombok.Data;

@Data
public class UnitDto {
	private Long id;
	private String code;
	private String name;
	private String baseUnit;
	private Double factor;
}
