package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeterTypeDto {
	private Long id;
	private Lang lang;
	private String name;
	private String manufacturer;
	private Double ratedCurrent;
	private Double ratedVoltage;
	private Double accuracyClass;
	private Double minimumLoad;
	private Long totalDigitsNumber;
	private Long digitsAfterDecimalPoint;
	private Boolean parameterAp;
	private Boolean parameterAm;
	private Boolean parameterRp;
	private Boolean parameterRm;
}
