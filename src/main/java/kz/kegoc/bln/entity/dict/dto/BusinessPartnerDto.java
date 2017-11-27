package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessPartnerDto {
	private Long id;
	private Lang lang;
	private String name;
	private String bin;
	private Long kbe;
	private String okpo;
	private String legalAddressName;
	private String actualAddressName;
	private Boolean isLargeConsumer;
	private Boolean isElectricityProducer;
	private Boolean isEnergySupplyOrganization;
	private Boolean isTransmittingOrganization;
	private Boolean isElectricityConsumer;
	private Long parentBusinessPartnerId;
}
