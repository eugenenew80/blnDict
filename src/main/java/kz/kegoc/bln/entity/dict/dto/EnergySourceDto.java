package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnergySourceDto {
	private Long id;
	private String code;
	private String name;
	private String shortName;
	private Long energySourceTypeId;
	private String energySourceTypeName;	
	private Double voltageClass;
	private String address;
	private Double installedPower;
}
