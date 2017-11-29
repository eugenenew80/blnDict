package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(of= {"id"})
public class PhoneNumber implements HasId, HasLang {
	private Long id;

	@NotNull @Size(max = 40)
	private String numberType;

	@NotNull
	private PhoneNumberType phoneNumber;

	@NotNull
	private Contact contact;

	private Lang lang;
}
