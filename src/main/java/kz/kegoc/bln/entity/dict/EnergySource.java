package kz.kegoc.bln.entity.dict;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.EnergySourceTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class EnergySource implements HasId, HasCode, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;
	private String shortName;

	@NotNull @Size(max = 15)
	private String code;

	@NotNull
	private EnergySourceType energySourceType;
	
	private Double voltageClass;
	private String address;
	private Double installedPower;
	private List<EnergySourceCompany> companies;
	private List<EnergySourceMeteringPoint> meteringPoints;
	private Map<Lang, EnergySourceTranslate> translations;
}
