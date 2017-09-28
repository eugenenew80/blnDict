package kz.kegoc.bln.entity.dict.dto;

import java.util.Date;

import lombok.*;

@Data
public class EnergySourceMeteringPointDto {
	private Long id;
	private Long energySourceId;
	private Long meteringPointId;
	private String meteringPointName;
	private Date startDate;
	private Date endDate;
}
