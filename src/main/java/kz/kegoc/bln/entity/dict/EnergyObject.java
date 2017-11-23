package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of= {"objectId", "object_type"})
public class EnergyObject implements HasName {
	private Long objectId;
	private String objectType;
	private String name;
	private String shortName;
}
