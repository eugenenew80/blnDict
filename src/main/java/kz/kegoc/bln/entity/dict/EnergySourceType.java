package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.EnergySourceTypeTranslate;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class EnergySourceType implements HasId, HasName, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 10)
	private String shortName;

	private Boolean res;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, EnergySourceTypeTranslate> translations;
	private Lang lang;
}
