package kz.kegoc.bln.entity.dict.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeteringPointMeterDto {
	private Long id;
	private Long meteringPointId;
	private Long meterId;
	private String meterName;
	private String meterSerialNumber;
	private String meterBusinessPartnerShortName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Lang lang;
}
