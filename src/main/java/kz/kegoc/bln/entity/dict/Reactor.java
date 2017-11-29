package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.HasName;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.translate.ReactorTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class Reactor implements HasId, HasName, HasLang {
	private Long id;

	@NotNull @Size(max = 30)
	private String shortName;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull
	private EnergyObject energyObject;

	private Double deltaPr;
	private Double unom;

	@NotNull
	private Organization org;

	private Map<Lang, ReactorTranslate> translations;
	private Lang lang;
}
