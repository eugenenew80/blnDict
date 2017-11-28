package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import java.util.Map;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.VoltageTransTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class VoltageTrans implements HasId, HasCode, HasName {
	private Long id;
	private Lang lang;
	
	@NotNull @Size(max = 15)
	private String code;

	@NotNull @Size(max = 100)
	private String name;
	
	@NotNull @Size(max = 100)
	private String manufacturer;

	@NotNull
	private Organization company;
	
	private Double ratedVoltage1;
	private Double ratedVoltage2;
	private Double accuracyClass;

	private BusinessPartner businessPartner;
	private Organization org;

	private Map<Lang, VoltageTransTranslate> translations;
}
