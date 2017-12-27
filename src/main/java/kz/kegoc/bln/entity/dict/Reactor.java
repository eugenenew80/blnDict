package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.ReactorTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class Reactor implements HasId, HasName, HasLang, HasDates, HasUser, HasOrg, IsEquip {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	private EnergyObject energyObject;

	@NotNull
	private Organization org;

	private BusinessPartner businessPartner;
	private Double deltaPr;
	private Double unom;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, ReactorTranslate> translations;
	private Lang lang;
}
