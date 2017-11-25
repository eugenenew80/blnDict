package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoltageTransTypeDto {
	private Long id;
	private Lang lang;
	private String code;
	private String name;
	private String manufacturer;
	private Double ratedVoltage1;
	private Double ratedVoltage2;
	private Double accuracyClass;
	private Double minVoltage;
	private Double maxVoltage;
}
