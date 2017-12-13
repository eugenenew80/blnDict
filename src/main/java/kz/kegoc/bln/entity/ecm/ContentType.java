package kz.kegoc.bln.entity.ecm;

import kz.kegoc.bln.entity.common.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class ContentType implements HasId, HasName, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max=100)
	private String name;

	@Size(max=300)
	private String description;

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Lang lang;
}
