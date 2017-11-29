package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeteringPointCharacteristicDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double voltagePermissibleLosses;
    private Double permissibleRelativeErrorLimit;
    private Double transformationRate;
    private Double minLoadInAccuracyClass;
    private Double maxLoadInAccuracyClass;
    private Long meteringPointId;
    private String meteringPointName;
    private Lang lang;
}
