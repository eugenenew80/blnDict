package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.EnergySourceTypeTranslate;
import lombok.*;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class EnergySourceType implements HasId, HasName {
	private Long id;
	private Lang lang;
	private String name;
	private String shortName;
	private Boolean isRes;
	private Map<Lang, EnergySourceTypeTranslate> translations;
}
