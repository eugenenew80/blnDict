package kz.kegoc.bln.entity.dict;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.SubstationTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class Substation implements HasId, HasCode, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;
	private String shortName;
	private String address;

	@NotNull @Size(max = 15)
	private String code;

	@NotNull
	private SubstationType substationType;

	@NotNull
	private VoltageClass voltageClass;

	private List<SubstationBusinessPartner> businessPartners;
	private List<SubstationMeteringPoint> meteringPoints;
	private Map<Lang, SubstationTranslate> translations;
}
