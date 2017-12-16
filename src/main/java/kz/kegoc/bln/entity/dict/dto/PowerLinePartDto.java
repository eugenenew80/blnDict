package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PowerLinePartDto {
	private Long id;
	private String name;
	private Long powerLineId;
	private String powerLineName;
	private Long businessPartnerId;
	private String businessPartnerShortName;
	private Double length;
	private Double r;
	private LocalDate startDate;
	private LocalDate endDate;
	private Long orgId;
	private String orgName;
	private Lang lang;
}
