package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeteringTypeDto {
	private Long id;
	private String name;
	private String shortName;
	private Boolean usedInEnergySources;
	private Boolean usedInSubstations;
	private Lang lang;
}
