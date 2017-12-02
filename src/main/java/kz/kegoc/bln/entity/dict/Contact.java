package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.ContactTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class Contact implements HasId, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull
	private ContactType contactType;

	@Size(max = 100)
	private String post;

	@NotNull @Size(max = 100)
	private String description;

	@NotNull
	private BusinessPartner businessPartner;

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private List<ContactEmail> contactEmails;
	private List<ContactPhoneNumber> contactPhoneNumbers;
	private Map<Lang, ContactTranslate> translations;
	private Lang lang;
}
