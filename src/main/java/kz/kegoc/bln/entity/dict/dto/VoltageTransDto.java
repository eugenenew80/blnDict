package kz.kegoc.bln.entity.dict.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoltageTransDto {
	private Long id;
	private String code;
	private String name;
	private String manufacturer;
	private Long companyId;
	private String companyName;
	private String serialNumber;	
	private Double ratedVoltage1;
	private Double ratedVoltage2;
	private Double accuracyClass;
	private Double minVoltage;
	private Double maxVoltage;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate lastVerificationDate;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate nextVerificationDate;
}
