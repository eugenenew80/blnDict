package kz.kegoc.bln.entity.dict.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnergySourceMeteringPointDto {
	private Long id;
	private Long energySourceId;
	private Long meteringPointId;
	private String meteringPointName;
	private Date startDate;
	private Date endDate;
}
