package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.AccountingTypeTranslate;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class AccountingType implements HasId, HasName, HasLang {
	private Long id;

	@NotNull @Size(max=100)
	private String name;

	private Lang lang;
	private Map<Lang, AccountingTypeTranslate> translations;
}
