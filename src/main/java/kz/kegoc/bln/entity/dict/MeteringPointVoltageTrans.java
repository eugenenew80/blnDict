package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasDates;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasUser;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.translate.MeteringPointVoltageTransTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringPointVoltageTrans implements HasId, HasDates, HasUser {
	private Long id;
	
	@NotNull
	private MeteringPoint meteringPoint;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 100)
	private String manufacturer;

	private BusinessPartner businessPartner;
	private Double ratedVoltage1;
	private Double ratedVoltage2;
	private Double accuracyClass;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, MeteringPointVoltageTransTranslate> translations;
	private Lang lang;
}
