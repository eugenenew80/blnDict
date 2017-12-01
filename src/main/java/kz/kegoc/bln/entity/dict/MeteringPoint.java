package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.MeteringPointTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringPoint implements HasId, HasCode, HasName, HasLang {
	private Long id;

	@NotNull @Size(max = 15)
	private String code;

	@NotNull @Size(max = 15)
	private String name;

	@NotNull @Size(max = 15)
	private String shortName;

	@Size(min = 18, max = 18)
	private String externalCode;

	@NotNull
	private BusinessPartner businessPartner1;

	@NotNull
	private BusinessPartner businessPartner2;

	@NotNull
	private AccountingType accountingType;

	@NotNull
	private MeteringPointType meteringPointType;

	@NotNull
	private EnergyObject energyObject;

	private LocalDate startDate;
	private LocalDate endDate;
	private Double ratedVoltage;
	private MeteringPoint referenceMeteringPoint;

	@NotNull
	private Organization org;

	private List<MeteringPointMeter> meters;
	private List<MeteringPointCurrentTrans> currentTrans;
	private List<MeteringPointVoltageTrans> voltageTrans;
	private List<MeteringPointCharacteristic> characteristics;
	private Map<Lang, MeteringPointTranslate> translations;
	private Lang lang;
}
