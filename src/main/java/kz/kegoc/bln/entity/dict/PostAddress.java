package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.HasName;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.translate.PostAddressTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class PostAddress implements HasId, HasName, HasLang {
	private Long id;

	@NotNull @Size(max = 300)
	private String name;

	@Size(max = 10)
	private String index;

	@NotNull
	private Country country;

	@NotNull
	private Region region;

	@Size(max = 100)
	private String locality;

	@Size(max = 100)
	private String street;

	@Size(max = 10)
	private String houseNumber;

	@Size(max = 10)
	private String buildingNumber;

	@Size(max = 10)
	private String apartment;

	@Size(max = 10)
	private String officeNumber;

	private Map<Lang, PostAddressTranslate> translations;
	private Lang lang;
}
