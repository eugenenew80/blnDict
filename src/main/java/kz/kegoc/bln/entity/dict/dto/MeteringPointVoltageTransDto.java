package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
	private String voltageTransSerialNumber;
	private LocalDate startDate;
	private LocalDate endDate;
}
