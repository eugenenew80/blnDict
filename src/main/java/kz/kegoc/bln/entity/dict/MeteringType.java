package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.MeteringTypeTranslate;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringType implements HasId, HasName, HasLang {
	private Long id;

	@NotNull @Size(max = 10)
	private String shortName;

	@NotNull @Size(max = 100)
	private String name;

	private Boolean usedInEnergySources;
	private Boolean usedInSubstations;
	private Map<Lang, MeteringTypeTranslate> translations;
	private Lang lang;
}
