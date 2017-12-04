package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.PowerLinePartTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class PowerLinePart implements HasId, HasName, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull
	private PowerLine powerLine;

	@NotNull
	private Organization org;

	private BusinessPartner businessPartner;
	private Double length;
	private Double r;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, PowerLinePartTranslate> translations;
	private Lang lang;
}
