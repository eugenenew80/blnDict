package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessPartnerDto {
	private Long id;
	private String name;
	private String bin;
	private Long kbe;
	private String okpo;
	private String certificateRegisterSeries;
	private LocalDate certificateRegisterDate;
	private String certificateRegisterNumber;
	private String certificateAuthorityBin;
	private Long businessPartnerId;
}
