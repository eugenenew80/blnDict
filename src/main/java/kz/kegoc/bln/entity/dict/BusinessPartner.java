package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import java.util.Map;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.HasName;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.translate.BusinessPartnerTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class BusinessPartner implements HasId, HasName, HasLang {
	private Long id;
	private Lang lang;
	private String name;
	private String certificateAuthorityName;

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

	private BusinessPartner bpParent;

	private Map<Lang, BusinessPartnerTranslate> translations;
}
