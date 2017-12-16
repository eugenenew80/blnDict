package kz.kegoc.bln.entity.dict;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.SubstationTranslate;
import lombok.*;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class Substation implements HasId, HasName, HasLang, HasDates, HasUser, HasOrg {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 30)
	private String shortName;

	@Size(max = 300)
	private String address;

	@NotNull
	private SubstationType substationType;

	@NotNull
	private VoltageClass voltageClass;

	@NotNull
	private Organization org;

	private BusinessPartner businessPartner;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private List<SubstationMeteringPoint> meteringPoints;
	private Map<Lang, SubstationTranslate> translations;
	private Lang lang;
}
