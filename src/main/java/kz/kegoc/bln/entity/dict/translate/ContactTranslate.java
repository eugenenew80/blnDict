package kz.kegoc.bln.entity.dict.translate;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Contact;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class ContactTranslate implements HasId, HasLang {
    private Long id;

    @NotNull
    private Lang lang;

    @NotNull
    private Contact contact;

    @NotNull @Size(max = 30)
    private String contactType;

    @Size(max = 100)
    private String post;

    @Size(max = 100)
    private String description;
}
