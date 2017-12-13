package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

import kz.kegoc.bln.entity.common.*;
import lombok.*;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringPointMeter implements HasId, HasLang, HasDates, HasUser {
	private Long id;
	
	@NotNull
	private MeteringPoint meteringPoint;

	@NotNull
	private Meter meter;
	
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Lang lang;
}
