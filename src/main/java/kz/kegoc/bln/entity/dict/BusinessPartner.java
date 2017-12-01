package kz.kegoc.bln.entity.dict;

import java.util.List;
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

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(min = 12, max = 12)
	private String bin;

	@NotNull @Size(min = 2, max = 2)
	private String kbe;

	@Size(max = 20)
	private String okpo;

	private PostAddress legalAddress;
	private PostAddress actualAddress;
	private Boolean largeConsumer;
	private Boolean electricityProducer;
	private Boolean energySupplyOrganization;
	private Boolean transmittingOrganization;
	private Boolean electricityConsumer;
	private BusinessPartner parentBusinessPartner;

	private List<Contact> contacts;
	private Map<Lang, BusinessPartnerTranslate> translations;
	private Lang lang;
}
