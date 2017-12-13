package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.MeteringTypeTranslate;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringType implements HasId, HasName, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 10)
	private String shortName;

	@NotNull @Size(max = 100)
	private String name;

	private Boolean usedInEnergySources;
	private Boolean usedInSubstations;
	private Map<Lang, MeteringTypeTranslate> translations;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Lang lang;
}
