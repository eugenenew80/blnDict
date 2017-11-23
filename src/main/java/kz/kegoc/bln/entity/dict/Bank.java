package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.BankTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class Bank implements HasId, HasCode, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;

	@NotNull @Size(max = 40)
	private String code;

	@NotNull @Size(max = 40)
	private String bic;

	@NotNull @Size(max = 40)
	private String bin;

	private Bank parentBank;

	private Map<Lang, BankTranslate> translations;
}
