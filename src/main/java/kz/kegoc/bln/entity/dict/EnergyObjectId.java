package kz.kegoc.bln.entity.dict;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(of= {"objectId, objectId"})
public class EnergyObjectId implements Serializable {
	private Long objectId;
	private String objectType;
}
