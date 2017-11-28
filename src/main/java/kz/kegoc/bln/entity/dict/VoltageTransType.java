package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.VoltageTransTypeTranslate;
import lombok.*;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class VoltageTransType implements HasId, HasName {
	private Long id;
	private Lang lang;
	private String name;
	private String manufacturer;
	private Double ratedVoltage1;
	private Double ratedVoltage2;
	private Double accuracyClass;
	private Map<Lang, VoltageTransTypeTranslate> translations;
}
