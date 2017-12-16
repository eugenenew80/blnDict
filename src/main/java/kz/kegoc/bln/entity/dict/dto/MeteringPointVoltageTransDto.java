package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeteringPointVoltageTransDto {
	private Long id;
	private Long meteringPointId;
	private String name;
	private String manufacturer;
	private Double ratedVoltage1;
	private Double ratedVoltage2;
	private Double accuracyClass;
	private Long businessPartnerId;
	private String businessPartnerShortName;
	private Lang lang;
	private LocalDate startDate;
	private LocalDate endDate;
}
