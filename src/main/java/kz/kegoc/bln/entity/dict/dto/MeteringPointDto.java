package kz.kegoc.bln.entity.dict.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeteringPointDto {
	private Long id;
	private String code;
	private String name;
	private String externalCode;
	private Double ratedVoltage;
	private Double limitError;
	private Double transformationRate;
	private Double minLoad;
	private Double maxLoad;	
	private Long firstBusinessPartnerId;
	private String firstBusinessPartnerName;
	private Long secondBusinessPartnerId;
	private String secondBusinessPartnerName;
	private Long meteringTypeId;
	private String meteringTypeName;
	private Long accountingTypeId;
	private String accountingTypeName;
	private Long meteringPointTypeId;
	private String meteringPointTypeName;
	private LocalDate startDate;
	private LocalDate endDate;
}
