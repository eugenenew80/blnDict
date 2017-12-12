package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.PowerLineTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class PowerLine implements HasId, HasName, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 30)
	private String code;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull
	private PowerLineType powerLineType;

	@NotNull
	private Organization org;

	@Size(max = 300)
	private String propertyBoundary;

	private Double length;
	private Double r;
	private Double po;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private List<PowerLinePart> powerLineParts;
	private Map<Lang, PowerLineTranslate> translations;
	private Lang lang;
}
