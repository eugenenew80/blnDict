package kz.kegoc.bln.entity.dict.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteringPointDto {
	private Long id;
	private String code;
	private String name;
	private String externalCode;
	private Date startDate;
	private Date endDate;
	private Double ratedVoltage;
	private Double limitError;
	private Double transformationRate;
	private Double minLoad;
	private Double maxLoad;	
	private Long firstCompanyId;
	private String firstCompanyName;
	private Long secondCompanyId;
	private String secondCompanyName;
	private Long meteringTypeId;
	private String meteringTypeName;
	private Long accountingTypeId;
	private String accountingTypeName;
	private Long meteringPointTypeId;
	private String meteringPointTypeName;
}
