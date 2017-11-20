package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasCode;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class VoltageClass implements HasId, HasCode {
	private Long id;

	@NotNull @Size(max = 60)
	private String code;

	@NotNull @Size(max = 100)
	private Double value;
}
