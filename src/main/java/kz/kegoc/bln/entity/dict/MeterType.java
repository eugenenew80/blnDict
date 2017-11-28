package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.MeterTypeTranslate;
import lombok.*;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeterType implements HasId, HasName {
	private Long id;
	private Lang lang;
	private String name;
	private String manufacturer;
	private Double ratedCurrent;
	private Double ratedVoltage;
	private Double accuracyClass;
	private Double minimumLoad;
	private Long totalDigitsNumber;
	private Long digitsAfterDecimalPoint;
	private Boolean parameterAp;
	private Boolean parameterAm;
	private Boolean parameterRp;
	private Boolean parameterRm;
	private Map<Lang, MeterTypeTranslate> translations;
}
