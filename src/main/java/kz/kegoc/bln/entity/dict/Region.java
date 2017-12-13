package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.RegionTranslate;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class Region implements HasId, HasCode, HasName, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 6)
	private String code;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull
	private Country country;

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, RegionTranslate> translations;
	private Lang lang;
}
