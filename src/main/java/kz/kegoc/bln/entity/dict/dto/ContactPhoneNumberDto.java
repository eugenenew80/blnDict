package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.common.PhoneNumberType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactPhoneNumberDto {
	private Long id;
	private PhoneNumberType numberType;
	private String phoneNumber;
	private Long contactId;
	private Lang lang;
}
