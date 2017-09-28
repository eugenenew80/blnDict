package kz.kegoc.bln.entity.dict.dto;

import java.util.Date;

import lombok.Data;

@Data
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
	private Date lastVerificationDate;
	private Date nextVerificationDate;	
}
