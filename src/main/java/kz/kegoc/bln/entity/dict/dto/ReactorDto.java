package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReactorDto {
	private Long id;
	private String name;
	private Long energyObjectId;
	private String energyObjectType;
	private String energyObjectName;
	private Double deltaPr;
	private Double unom;
	private Long businessPartnerId;
	private String businessPartnerShortName;
	private Long orgId;
	private String orgName;
	private LocalDateTime lastUpdateDate;
	private Lang lang;
}
