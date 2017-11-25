package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.VoltageTransTypeTranslate;
import lombok.*;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class VoltageTransType implements HasId, HasCode, HasName {
	private Long id;
	private Lang lang;
	
	@NotNull @Size(max = 15)
	private String code;

	@NotNull @Size(max = 100)
	private String name;
	
	@NotNull @Size(max = 100)
	private String manufacturer;

	private Double ratedVoltage1;
	private Double ratedVoltage2;
	private Double accuracyClass;
	private Double minVoltage;
	private Double maxVoltage;

	private Map<Lang, VoltageTransTypeTranslate> translations;
}
