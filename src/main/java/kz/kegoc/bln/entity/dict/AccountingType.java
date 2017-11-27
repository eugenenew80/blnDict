package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.AccountingTypeTranslate;
import lombok.*;

import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class AccountingType implements HasId, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;
	private Map<Lang, AccountingTypeTranslate> translations;
}
