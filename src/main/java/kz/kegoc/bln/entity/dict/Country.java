package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.CountryTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class Country implements HasId, HasCode, HasName, HasLang {
	private Long id;

	@NotNull @Size(max = 2)
	private String code;

	@NotNull @Size(max = 100)
	private String name;

	private Map<Lang, CountryTranslate> translations;
	private Lang lang;
}
