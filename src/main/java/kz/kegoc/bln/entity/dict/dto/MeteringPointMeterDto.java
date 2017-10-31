package kz.kegoc.bln.entity.dict.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeteringPointMeterDto {
	private Long id;
	private Long meteringPointId;
	private Long meterId;
	private String meterCode;
	private String meterName;
	private String meterSerialNumber;
	private LocalDate startDate;
	private LocalDate endDate;
}
