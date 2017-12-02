package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.HasDates;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasUser;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of= {"id"})
public class BankAccount implements HasId, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 40)
	private String bankAccountNumber;

	@NotNull
	private Bank bank;

	@NotNull
	private BusinessPartner businessPartner;

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Lang lang;
}
