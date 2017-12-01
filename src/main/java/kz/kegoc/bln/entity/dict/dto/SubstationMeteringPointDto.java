package kz.kegoc.bln.entity.dict.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubstationMeteringPointDto {
	private Long id;
	private Long substationId;
	private Long meteringPointId;
	private String meteringPointName;
	private Long meteringTypeId;
	private String meteringTypeName;
	private Boolean needReverse;
	private LocalDate startDate;
	private LocalDate endDate;
	private Lang lang;
}
