package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.HasName;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.translate.PowerLinePartTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class PowerLinePart implements HasId, HasName, HasLang {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull
	private PowerLine powerLine;

	@NotNull
	private BusinessPartner businessPartner;

	private Double length;
	private Double r;
	private LocalDate startDate;
	private LocalDate endDate;
	private Map<Lang, PowerLinePartTranslate> translations;
	private Lang lang;
}
