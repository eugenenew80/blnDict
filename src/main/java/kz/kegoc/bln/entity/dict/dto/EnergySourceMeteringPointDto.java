package kz.kegoc.bln.entity.dict.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnergySourceMeteringPointDto {
	private Long id;
	private Long energySourceId;
	private Long meteringPointId;
	private String meteringPointName;
	private LocalDate startDate;
	private LocalDate endDate;
}
