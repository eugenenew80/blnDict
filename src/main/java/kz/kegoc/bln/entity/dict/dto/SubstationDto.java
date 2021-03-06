package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubstationDto {
	private Long id;
	private String name;
	private String shortName;
	private Long energyNodeId;
	private String energyNodeName;
	private Long energyDistrictId;
	private String energyDistrictName;
	private Long substationTypeId;
	private String substationTypeName;
	private Long voltageClassId;
	private String voltageClassName;
	private Double voltageClassValue;
	private String address;
	private Long businessPartnerId;
	private String businessPartnerShortName;
	private Long orgId;
	private String orgName;
	private Lang lang;
}
