package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitDto {
	private Long id;
	private String code;
	private String name;
	private String baseUnit;
	private Double factor;
}
