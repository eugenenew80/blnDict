package kz.kegoc.bln.entity.dict.translate;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.Contact;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class ContactTranslate implements HasId, HasLang, HasDates, HasUser {
    private Long id;

    @NotNull
    private Lang lang;

    @Size(max = 100)
    private String post;

    @NotNull @Size(max = 100)
    private String description;

    @NotNull
    private Contact contact;

    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private User createBy;
    private User lastUpdateBy;
}
