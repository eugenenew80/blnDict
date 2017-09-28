package kz.kegoc.bln.entity.dict.dto;

import java.util.Date;
import lombok.Data;

@Data
public class MeterDto {
	private Long id;
	private String code;
	private String name;
	private String manufacturer;
	private String serialNumber;
	private Double ratedCurrent;
	private Double ratedVoltage;
	private Double accuracyClass;
	private Double minimumLoad;
	private Double maximumLoad;
	private Long companyId;
	private String companyName;
	private Date lastVerificationDate;
	private Date nextVerificationDate;	
}
