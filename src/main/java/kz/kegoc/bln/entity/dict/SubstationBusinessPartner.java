package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.*;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class SubstationBusinessPartner implements HasId, HasLang, HasDates, HasUser {
	private Long id;
	
	@NotNull
	private Substation substation;

	@NotNull
	private BusinessPartner businessPartner;
	
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Lang lang;
}
