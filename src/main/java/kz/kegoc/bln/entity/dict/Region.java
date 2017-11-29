package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.RegionTranslate;
import lombok.*;

import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class Region implements HasId, HasCode, HasName, HasLang {
	private Long id;

	@NotNull @Size(max = 6)
	private String code;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull
	private Country country;

	private Map<Lang, RegionTranslate> translations;
	private Lang lang;
}
