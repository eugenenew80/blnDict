package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import lombok.*;

import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class AccountingType implements HasId, HasCode, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;

	@NotNull @Size(max = 10)
	private String code;

	private Map<Lang, AccountingType> translations;
}
