package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasCode;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(of= {"id"})
public class Bank implements HasId, HasCode, HasName {
	private Long id;

	@NotNull @Size(max = 40)
	private String code;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 40)
	private String bic;

	@NotNull @Size(max = 40)
	private String bin;

	@NotNull
	private List<Bank> parentBank;
}
