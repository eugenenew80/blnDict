package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.MeteringPointTypeTranslate;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringPointType implements HasId, HasCode, HasName, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 10)
	private String code;

	@NotNull @Size(max = 100)
	private String name;

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, MeteringPointTypeTranslate> translations;
	private Lang lang;
}
