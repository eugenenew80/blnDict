package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class ContactEmail implements HasId, HasLang {
	private Long id;
	private Lang lang;

	@NotNull @Size(max = 100)
	private String email;

	@Size(max = 100)
	private String description;

	@NotNull
	private Contact contact;
}
