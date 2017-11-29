package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringPointVoltageTrans implements HasId {
	private Long id;
	
	@NotNull
	private MeteringPoint meteringPoint;

	@NotNull
	private VoltageTrans voltageTrans;
	
	private LocalDate startDate;
	private LocalDate endDate;
	private Lang lang;
}
