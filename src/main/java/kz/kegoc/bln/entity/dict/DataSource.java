package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.DataSourceTranslate;
import lombok.*;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class DataSource implements HasId, HasCode, HasName {
	private Long id;

	@NotNull @Size(max = 15)
	private String code;

	@NotNull @Size(max = 100)
	private String name;

	private Map<Lang, DataSourceTranslate> translations;
	private Lang lang;
}
