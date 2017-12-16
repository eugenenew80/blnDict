package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.PowerTransformerTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class PowerTransformer implements HasId, HasName, HasLang, HasDates, HasUser, HasOrg {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	private EnergyObject energyObject;

	@NotNull
	private Organization org;

	private Double snom;
	private Double deltaPxx;
	private Double unomH;
	private Double pkzHm;
	private Double pkzHl;
	private Double pkzMl;
	private BusinessPartner businessPartner;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, PowerTransformerTranslate> translations;
	private Lang lang;
}
