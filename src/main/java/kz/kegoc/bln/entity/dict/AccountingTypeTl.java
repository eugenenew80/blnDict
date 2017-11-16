package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class AccountingTypeTl implements HasId, HasName {
	private Long id;

	@NotNull
	private AccountingType accountingType;

	@NotNull @Size(max = 2)
	private String lang;

	@NotNull @Size(max = 100)
	private String name;
}
