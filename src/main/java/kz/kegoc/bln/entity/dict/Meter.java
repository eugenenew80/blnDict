package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import java.util.Map;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.MeterTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class Meter implements HasId, HasCode, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;
	private String manufacturer;

	@NotNull @Size(max = 15)
	private String code;

	@NotNull
	private BusinessPartner businessPartner;
	
	private String serialNumber;
	private Double ratedCurrent;
	private Double ratedVoltage;
	private Double accuracyClass;
	private Double minimumLoad;
	private Double maximumLoad;
	private LocalDate lastVerificationDate;
	private LocalDate nextVerificationDate;

	private Map<Lang, MeterTranslate> translations;
}
