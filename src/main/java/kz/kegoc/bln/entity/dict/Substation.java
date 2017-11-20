package kz.kegoc.bln.entity.dict;

import java.util.List;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class Substation implements HasId, HasCode, HasName {
	private Long id;
	
	@NotNull @Size(max = 15)
	private String code;

	@NotNull @Size(max = 100)
	private String name;
	
	@NotNull @Size(max = 10)
	private String shortName;
	
	@NotNull
	private SubstationType substationType;
	
	private Double voltageClass;
	private String address;
	private List<SubstationCompany> companies;
	private List<SubstationMeteringPoint> meteringPoints;
	private List<VoltageClass> voltageClasses;
}
