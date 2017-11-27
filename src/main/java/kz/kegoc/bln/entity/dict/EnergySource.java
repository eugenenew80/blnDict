package kz.kegoc.bln.entity.dict;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.EnergySourceTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class EnergySource implements HasId, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;
	private String shortName;
	private String address;
	private Double installedPower;

	@NotNull
	private EnergySourceType energySourceType;

	@NotNull
	private VoltageClass voltageClass;

	@NotNull
	private Organization org;

	private List<EnergySourceBusinessPartner> businessPartners;
	private List<EnergySourceMeteringPoint> meteringPoints;
	private Map<Lang, EnergySourceTranslate> translations;
}
