package kz.kegoc.bln.entity.ecm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessPartnerContentDto {
	private Long id;
	private String name;
	private String sourceTable;
	private Long businessPartnerId;
	private String fileName;
	private String fileExt;
	private Long fileSize;
	private Long contentTypeId;
	private String contentTypeName;
	private String contentBase64;
	private Lang lang;
}
