package kz.kegoc.bln.entity.dict.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnergySourceCompanyDto {
	private Long id;
	private Long energySourceId;
	private Long companyId;
	private String companyName;
	private LocalDate startDate;
	private LocalDate endDate;
}
