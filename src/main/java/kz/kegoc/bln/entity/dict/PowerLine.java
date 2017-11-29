package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.HasName;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.translate.PowerLineTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class PowerLine implements HasId, HasName, HasLang {
	private Long id;

	@NotNull @Size(max = 30)
	private String shortName;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull
	private PowerLineType powerLineType;

	@Size(max = 300)
	private String propertyBoundary;

	private Double length;
	private Double r;
	private Double po;
	private Map<Lang, PowerLineTranslate> translations;
	private Lang lang;
}
