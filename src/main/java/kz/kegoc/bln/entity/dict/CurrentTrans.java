package kz.kegoc.bln.entity.dict;

import java.util.Map;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.CurrentTransTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class CurrentTrans implements HasId, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;
	private String manufacturer;
	private Double ratedCurrent1;
	private Double ratedCurrent2;
	private Double accuracyClass;
	private Double minCurrent;
	private Double maxCurrent;

	@NotNull
	private Organization org;

	private Map<Lang, CurrentTransTranslate> translations;
}
