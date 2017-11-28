package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.translate.ContactTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class Contact implements HasId, HasLang {
	private Long id;
	private Lang lang;
	private String contactType;
	private String post;
	private String description;

	@NotNull
	private BusinessPartner businessPartner;

	private Map<Lang, ContactTranslate> translations;
}
