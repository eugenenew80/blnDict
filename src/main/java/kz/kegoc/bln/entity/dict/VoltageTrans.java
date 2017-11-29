package kz.kegoc.bln.entity.dict;

import java.util.Map;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.VoltageTransTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class VoltageTrans implements HasId, HasName, HasLang {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 100)
	private String manufacturer;

	private Double ratedVoltage1;
	private Double ratedVoltage2;
	private Double accuracyClass;

	@NotNull
	private BusinessPartner businessPartner;

	@NotNull
	private Organization org;

	private Map<Lang, VoltageTransTranslate> translations;
	private Lang lang;
}
