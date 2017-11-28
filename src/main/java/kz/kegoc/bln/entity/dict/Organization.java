package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.OrganizationTranslate;
import lombok.*;

import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class Organization implements HasId, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;

	@Size(min=12, max = 12)
	private String bin;

	@NotNull
	private OrganizationType orgType;

	private Organization parentOrg;
	private Map<Lang, OrganizationTranslate> translations;
}
