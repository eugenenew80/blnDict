package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringPoint implements HasId, HasCode, HasName {
	private Long id;
	
	@NotNull @Size(max = 10)
	private String code;

	@NotNull @Size(max = 100)
	private String name;

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
}
