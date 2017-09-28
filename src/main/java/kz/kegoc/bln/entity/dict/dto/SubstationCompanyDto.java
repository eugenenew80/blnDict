package kz.kegoc.bln.entity.dict.dto;

import java.util.Date;

import lombok.*;

@Data
public class SubstationCompanyDto {
	private Long id;
	private Long substationId;
	private Long companyId;
	private String companyName;
	private Date startDate;
	private Date endDate;
}
