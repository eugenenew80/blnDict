package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.MeteringTypeTranslate;
import lombok.*;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringType implements HasId, HasName {
	private Long id;
	private Lang lang;
	private String name;
	private String shortName;
	private Boolean usedInEnergySources;
	private Boolean usedInSubstations;
	private Map<Lang, MeteringTypeTranslate> translations;
}
