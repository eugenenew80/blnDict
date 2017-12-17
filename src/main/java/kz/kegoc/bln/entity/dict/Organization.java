package kz.kegoc.bln.entity.dict;

import javax.validation.constraints.*;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.OrganizationTranslate;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class Organization implements HasId, HasName, HasLang, HasDates, HasUser {
	public Organization() {}
	public Organization(Long id) { setId(id);}

	private Long id;

	@NotNull @Size(max = 250)
	private String name;

	@NotNull @Size(max = 100)
	private String shortName;

	@Size(min=12, max = 12)
	private String bin;

	@NotNull
	private OrgType orgType;

	private Organization parentOrg;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, OrganizationTranslate> translations;
	private Lang lang;
}
