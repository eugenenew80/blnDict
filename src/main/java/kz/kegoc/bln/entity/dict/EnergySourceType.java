package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class EnergySourceType implements HasId, HasCode, HasName {
	private Long id;
	
	@NotNull @Size(max = 10)
	private String code;

	@NotNull @Size(max = 100)
	private String name;
	
	@NotNull @Size(max = 10)
	private String shortName;

	private Boolean isRes;
}
