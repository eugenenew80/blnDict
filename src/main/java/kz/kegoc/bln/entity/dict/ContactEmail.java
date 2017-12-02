package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.ContactEmailTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class ContactEmail implements HasId, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max = 30)
	private String email;

	@Size(max = 100)
	private String description;

	@NotNull
	private Contact contact;

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Map<Lang, ContactEmailTranslate> translations;
	private Lang lang;
}
