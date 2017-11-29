package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringPointCharacteristic implements HasId,  HasLang {
	private Long id;

	@NotNull
	private MeteringPoint meteringPoint;

	private LocalDate startDate;
	private LocalDate endDate;
	private Double voltagePermissibleLosses;
	private Double permissibleRelativeErrorLimit;
	private Double transformationRate;
	private Double minLoadInAccuracyClass;
	private Double maxLoadInAccuracyClass;
	private Lang lang;
}
