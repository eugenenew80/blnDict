package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.UnitTranslate;
import lombok.*;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class Unit implements HasId, HasCode, HasName {
	private Long id;
	private Lang lang;
	
	@NotNull @Size(max = 10)
	private String code;

	@NotNull @Size(max = 100)
	private String name;

	private Unit baseUnit;

	private Double factor;

	private Map<Lang, UnitTranslate> translations;
}
