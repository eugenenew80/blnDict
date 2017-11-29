package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.SubstationTypeTranslate;
import lombok.*;

import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class SubstationType implements HasId, HasName {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 10)
	private String shortName;

	private Map<Lang, SubstationTypeTranslate> translations;
	private Lang lang;
}
