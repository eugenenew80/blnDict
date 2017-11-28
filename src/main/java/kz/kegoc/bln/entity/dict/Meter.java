package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import java.util.Map;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.MeterTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class Meter implements HasId, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;
	private String manufacturer;

	@NotNull
	private BusinessPartner businessPartner;
	
	private String serialNumber;
	private Double ratedCurrent;
	private Double ratedVoltage;
	private Double accuracyClass;
	private Double minimumLoad;
	private LocalDate lastVerificationDate;
	private LocalDate nextVerificationDate;
	private Long totalDigitsNumber;
	private Long digitsAfterDecimalPoint;
	private Boolean parameterAp;
	private Boolean parameterAm;
	private Boolean parameterRp;
	private Boolean parameterRm;
	private Boolean withdrawn;

	@NotNull
	private Organization org;

	private Map<Lang, MeterTranslate> translations;
}
