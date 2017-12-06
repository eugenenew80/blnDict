package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PowerTransformerDto {
	private Long id;
	private String name;
	private Long energyObjectId;
	private String energyObjectType;
	private String energyObjectName;
	private Long businessPartnerId;
	private String businessPartnerName;
	private Double snom;
	private Double deltaPxx;
	private Double unomH;
	private Double pkzHm;
	private Double pkzHl;
	private Double pkzMl;
	private Long orgId;
	private String orgName;
	private Lang lang;
}
