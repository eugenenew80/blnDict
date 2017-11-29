package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.translate.ContactTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class Contact implements HasId, HasLang {
	private Long id;

	@NotNull @Size(max = 30)
	private String contactType;

	@Size(max = 100)
	private String post;

	@NotNull @Size(max = 100)
	private String description;

	@NotNull
	private BusinessPartner businessPartner;

	private Map<Lang, ContactTranslate> translations;
	private Lang lang;
}
