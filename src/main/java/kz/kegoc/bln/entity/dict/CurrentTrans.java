package kz.kegoc.bln.entity.dict;

import java.time.LocalDateTime;
import java.util.Map;
import javax.validation.constraints.*;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.CurrentTransTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class CurrentTrans implements HasId, HasName, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 100)
	private String manufacturer;

	private Double ratedCurrent1;
	private Double ratedCurrent2;
	private Double accuracyClass;
	private Double minCurrent;
	private Double maxCurrent;

	@NotNull
	private BusinessPartner businessPartner;

	@NotNull
	private Organization org;

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, CurrentTransTranslate> translations;
	private Lang lang;
}
