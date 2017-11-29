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

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 30)
	private String shortName;

	@Size(max = 300)
	private String address;

	private Double installedPower;
	private Boolean largeElectricityProducer;

	@NotNull
	private EnergySourceType energySourceType;

	@NotNull
	private VoltageClass voltageClass;

	@NotNull
	private Organization org;

	private List<EnergySourceBusinessPartner> businessPartners;
	private List<EnergySourceMeteringPoint> meteringPoints;
	private Map<Lang, EnergySourceTranslate> translations;
	private Lang lang;
}
