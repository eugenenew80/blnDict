package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnergySourceDto {
	private Long id;
	private Lang lang;
	private String name;
	private String shortName;
	private Long energySourceTypeId;
	private String energySourceTypeName;	
	private Long voltageClassId;
	private String voltageClassName;
	private String address;
	private Double installedPower;
	private Long orgId;
	private String orgName;
}
