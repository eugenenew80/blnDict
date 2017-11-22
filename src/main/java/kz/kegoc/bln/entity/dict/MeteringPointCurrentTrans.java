package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringPointCurrentTrans implements HasId {
	private Long id;
	
	@NotNull
	private MeteringPoint meteringPoint;

	@NotNull
	private CurrentTrans currentTrans;
	
	private LocalDate startDate;
	private LocalDate endDate;
}
