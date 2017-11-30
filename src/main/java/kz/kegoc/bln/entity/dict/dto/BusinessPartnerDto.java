package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessPartnerDto {
	private Long id;
	private String name;
	private String bin;
	private String kbe;
	private String okpo;
	private String legalAddressName;
	private String actualAddressName;
	private Boolean largeConsumer;
	private Boolean electricityProducer;
	private Boolean energySupplyOrganization;
	private Boolean transmittingOrganization;
	private Boolean electricityConsumer;
	private Long parentBusinessPartnerId;
	private String parentBusinessPartnerName;
	private Lang lang;
}
