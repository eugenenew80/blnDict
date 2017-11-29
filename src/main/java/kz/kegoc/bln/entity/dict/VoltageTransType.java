package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.VoltageTransTypeTranslate;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class VoltageTransType implements HasId, HasName {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 100)
	private String manufacturer;

	private Double ratedVoltage1;
	private Double ratedVoltage2;
	private Double accuracyClass;
	private Map<Lang, VoltageTransTypeTranslate> translations;
	private Lang lang;
}
