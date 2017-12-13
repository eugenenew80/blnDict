package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.MeteringPointCurrentTransTranslate;
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
public class MeteringPointCurrentTrans implements HasId, HasLang, HasDates, HasUser {
	private Long id;
	
	@NotNull
	private MeteringPoint meteringPoint;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 100)
	private String manufacturer;

	private BusinessPartner businessPartner;
	private Double ratedCurrent1;
	private Double ratedCurrent2;
	private Double accuracyClass;
	private Double minCurrent;
	private Double maxCurrent;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, MeteringPointCurrentTransTranslate> translations;
	private Lang lang;
}
