package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import java.util.Map;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.CurrentTransTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class CurrentTrans implements HasId, HasCode, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;
	private String manufacturer;

	@NotNull @Size(max = 15)
	private String code;

	@NotNull
	private Company company;
	
	private String serialNumber;
	private Double ratedCurrent1;
	private Double ratedCurrent2;
	private Double accuracyClass;
	private Double minCurrent;
	private Double maxCurrent;
	private LocalDate lastVerificationDate;
	private LocalDate nextVerificationDate;
	private LocalDate installationDate;
	private LocalDate disassemblyDate;

	private Map<Lang, CurrentTransTranslate> translations;
}
