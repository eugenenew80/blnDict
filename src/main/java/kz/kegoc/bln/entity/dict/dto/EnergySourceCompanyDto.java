package kz.kegoc.bln.entity.dict.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnergySourceCompanyDto {
	private Long id;
	private Long energySourceId;
	private Long companyId;
	private String companyName;
	private Date startDate;
	private Date endDate;
}
