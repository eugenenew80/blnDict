package kz.kegoc.bln.entity.dict.translate;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.HasName;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.VoltageClass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class VoltageClassTranslate implements HasId, HasName, HasLang {
    private Long id;

    @NotNull
    private Lang lang;

    @NotNull @Size(max = 10)
    private String name;

    @NotNull
    private VoltageClass voltageClass;
}
