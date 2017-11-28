package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.MeterTypeTranslate;
import lombok.*;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeterType implements HasId, HasCode, HasName {
	private Long id;
	private Lang lang;
	
	@NotNull @Size(max = 15)
	private String code;

	@NotNull @Size(max = 100)
	private String name;
	
	@NotNull @Size(max = 100)
	private String manufacturer;

	private Double ratedCurrent;
	private Double ratedVoltage;
	private Double accuracyClass;
	private Double minimumLoad;
	private Long totalDigitsNumber;
	private Long digitsAfterDecimalPoint;

	private Map<Lang, MeterTypeTranslate> translations;
}
