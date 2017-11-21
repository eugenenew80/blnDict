package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasCode;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(of= {"id"})
public class PhoneNumber implements HasId {
	private Long id;

	@NotNull @Size(max = 40)
	private String numberType;

	@NotNull @Size(max = 15)
	private String phoneNumber;

	@NotNull
	private BusinessPartner businessPartner;
}
