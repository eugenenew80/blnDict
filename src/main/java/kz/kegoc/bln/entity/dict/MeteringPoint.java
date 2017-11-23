package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.MeteringPointTranslate;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringPoint implements HasId, HasCode, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;

	@NotNull @Size(max = 10)
	private String code;

	@Size(max = 18)
	private String externalCode;

	@NotNull
	private BusinessPartner firstBusinessPartner;
	
	@NotNull
	private BusinessPartner secondBusinessPartner;
	
	@NotNull
	private MeteringType meteringType;
	
	@NotNull
	private AccountingType accountingType;

	@NotNull
	private MeteringPointType meteringPointType;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="object_id", referencedColumnName="object_id"),
		@JoinColumn(name="object_type", referencedColumnName="object_type")
	})
	private EnergyObject energyObject;

	private LocalDate startDate;
	private LocalDate endDate;
	private Double ratedVoltage;
	private Double limitError;
	private Double transformationRate;
	private Double minLoad;
	private Double maxLoad;
	
	private List<MeteringPointMeter> meters;
	private List<MeteringPointCurrentTrans> currentTrans;
	private List<MeteringPointVoltageTrans> voltageTrans;
	private Map<Lang, MeteringPointTranslate> translations;
}
