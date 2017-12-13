package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringPointCharacteristic implements HasId, HasLang, HasDates, HasUser {
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
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Lang lang;
}
