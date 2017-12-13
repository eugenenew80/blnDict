package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.CountryTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class Country implements HasId, HasCode, HasName, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 2)
	private String code;

	@NotNull @Size(max = 100)
	private String name;

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, CountryTranslate> translations;
	private Lang lang;
}
