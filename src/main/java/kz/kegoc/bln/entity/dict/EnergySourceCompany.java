package kz.kegoc.bln.entity.dict;

import java.util.Date;
import javax.validation.constraints.NotNull;
import kz.kegoc.bln.entity.common.HasId;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class EnergySourceCompany implements HasId {
	private Long id;
	
	@NotNull
	private EnergySource energySource;

	@NotNull
	private Company company;
	
	private Date startDate;
	private Date endDate;
}
