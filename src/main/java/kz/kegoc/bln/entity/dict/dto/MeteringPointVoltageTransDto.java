package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeteringPointVoltageTransDto {
	private Long id;
	private Long meteringPointId;
	private Long voltageTransId;
	private String voltageTransName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Lang lang;
}
