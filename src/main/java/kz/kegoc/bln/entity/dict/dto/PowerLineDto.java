package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PowerLineDto {
	private Long id;
	private String code;
	private String name;
	private Long powerLineTypeId;
	private String powerLineTypeName;
	private String propertyBoundary;
	private Double length;
	private Double r;
	private Double po;
	private Long orgId;
	private String orgName;
	private Lang lang;
}
