package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class ContactPhoneNumber implements HasId, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull
	private PhoneNumberType numberType;

	@NotNull @Size(max = 15)
	private String  phoneNumber;

	@NotNull
	private Contact contact;

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Lang lang;
}
