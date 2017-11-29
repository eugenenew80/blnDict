package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class BankAccount implements HasId {
	private Long id;

	@NotNull @Size(max = 40)
	private String bankAccountNumber;

	@NotNull
	private Bank bank;

	@NotNull
	private BusinessPartner businessPartner;

	private Lang lang;
}
