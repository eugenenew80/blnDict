package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.EnergyNodeTranslate;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class EnergyNode implements HasId, HasName, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 10)
	private String shortName;

	@NotNull
	private Region region;

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, EnergyNodeTranslate> translations;
	private Lang lang;
}
