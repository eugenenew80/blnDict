package kz.kegoc.bln.entity.dict.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubstationBusinessPartnerDto {
	private Long id;
	private Long substationId;
	private Long businessPartnerId;
	private String businessPartnerName;
	private LocalDate startDate;
	private LocalDate endDate;
}
