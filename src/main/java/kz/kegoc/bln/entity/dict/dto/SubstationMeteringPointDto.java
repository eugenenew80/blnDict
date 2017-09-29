package kz.kegoc.bln.entity.dict.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubstationMeteringPointDto {
	private Long id;
	private Long substationId;
	private Long meteringPointId;
	private String meteringPointName;
	private Date startDate;
	private Date endDate;
}
