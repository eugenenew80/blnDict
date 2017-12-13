package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.PostAddressTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class PostAddress implements HasId, HasName, HasLang, HasDates, HasUser {
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

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, PostAddressTranslate> translations;
	private Lang lang;
}
