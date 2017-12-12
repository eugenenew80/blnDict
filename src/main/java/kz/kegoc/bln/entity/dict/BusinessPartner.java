package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.BusinessPartnerTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class BusinessPartner implements HasId, HasName, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(min = 12, max = 12)
	private String bin;

	@Size(min = 2, max = 2)
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
	private String certNum;
	private String certSer;
	private LocalDate certDate;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private List<Contact> contacts;
	private List<BankAccount> bankAccounts;
	private Map<Lang, BusinessPartnerTranslate> translations;
	private Lang lang;
}
