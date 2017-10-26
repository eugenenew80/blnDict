package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import kz.kegoc.bln.entity.common.HasId;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class EnergySourceMeteringPoint implements HasId {
	private Long id;
	
	@NotNull
	private EnergySource energySource;

	@NotNull
	private MeteringPoint meteringPoint;
	
	private LocalDate startDate;
	private LocalDate endDate;
}
