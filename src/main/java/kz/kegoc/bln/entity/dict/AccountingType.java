package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import lombok.*;
import java.util.List;

@Data
@EqualsAndHashCode(of= {"id"})
public class AccountingType implements HasId, HasCode, HasName {
	private Long id;

	@NotNull @Size(max = 10)
	private String code;

	@NotNull @Size(max = 100)
	private String name;

	private List<AccountingTypeTl> translations;
}
