package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class BusinessPartner implements HasId, HasName {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 12)
	private String bin;

	@NotNull
	private Long kbe;

	@NotNull  @Size(max = 10)
	private String okpo;

	@Size(max = 60)
	private String certificateRegisterSeries;

	@NotNull
	private LocalDate certificateRegisterDate;

	@NotNull @Size(max = 60)
	private String certificateRegisterNumber;

	@NotNull @Size(max = 150)
	private String certificateAuthorityBin;

	@NotNull @Size(max = 150)
	private String certificateAuthorityName;

	private BusinessPartner bpParent;
}
