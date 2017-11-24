package kz.kegoc.bln.entity.dict.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrentTransDto {
	private Long id;
	private Lang lang;
	private String code;
	private String name;
	private String manufacturer;
	private Long companyId;
	private String companyName;
	private String serialNumber;
	private Double ratedCurrent1;
	private Double ratedCurrent2;
	private Double accuracyClass;
	private Double minCurrent;
	private Double maxCurrent;
	private LocalDate lastVerificationDate;
	private LocalDate nextVerificationDate;
	private LocalDate installationDate;
	private LocalDate disassemblyDate;
}
