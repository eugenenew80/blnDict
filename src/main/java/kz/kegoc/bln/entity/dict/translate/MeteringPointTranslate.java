package kz.kegoc.bln.entity.dict.translate;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class MeteringPointTranslate implements HasId, HasName, HasLang, HasDates, HasUser {
    private Long id;

    @NotNull
    private Lang lang;

    @NotNull
    private MeteringPoint meteringPoint;

    @NotNull @Size(max = 100)
    private String name;

    @NotNull @Size(max = 100)
    private String shortName;

    @Size(max = 500)
    private String propertyBoundary;

    @Size(max = 500)
    private String responsibilityZone1;

    @Size(max = 500)
    private String responsibilityZone2;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private User createBy;
    private User lastUpdateBy;
}
