package kz.kegoc.bln.entity.dict.translate;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.HasName;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Bank;
import kz.kegoc.bln.entity.dict.Company;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class CompanyTranslate implements HasId, HasName, HasLang {
    private Long id;

    @NotNull
    private Lang lang;

    @NotNull
    private Company company;

    @NotNull @Size(max = 100)
    private String name;
}
