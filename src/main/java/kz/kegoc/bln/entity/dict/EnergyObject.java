package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasCode;
import kz.kegoc.bln.entity.common.HasName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;

@Data
@EqualsAndHashCode(of= {"id"})
@Entity @IdClass(EnergyObjectId.class)
@Table(name = "dict_energy_objects_v", schema = "apps")
public class EnergyObject implements HasCode, HasName {
	@Id
	@Column(name = "object_id")
	private Long objectId;

	@Id
	@Column(name = "object_type")
	private String objectType;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "short_name")
	private String shortName;
}
