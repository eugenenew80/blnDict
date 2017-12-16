package kz.kegoc.bln.entity.dict.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeteringPointDto {
	private Long id;
	private String code;
	private String shortName;
	private String name;
	private String externalCode;
	private Double ratedVoltage;
	private Long businessPartnerId1;
	private Long businessPartnerId2;
	private String businessPartnerShortName1;
	private String businessPartnerShortName2;
	private String propertyBoundary;
	private String responsibilityZone1;
	private String responsibilityZone2;
	private Long accountingTypeId;
	private String accountingTypeName;
	private Long meteringPointTypeId;
	private String meteringPointTypeName;
	private Long energyObjectId;
	private String energyObjectType;
	private String energyObjectName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Long referenceMeteringPointId;
	private String referenceMeteringPointName;
	private Long orgId;
	private String orgShortName;
	private Lang lang;
}
