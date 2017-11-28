package kz.kegoc.bln.entity.dict.translate;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.ContactEmail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class ContactEmailTranslate implements HasId, HasLang {
    private Long id;

    @NotNull
    private Lang lang;

    @NotNull
    private ContactEmail contactEmail;

    @NotNull @Size(max = 100)
    private String description;
}
