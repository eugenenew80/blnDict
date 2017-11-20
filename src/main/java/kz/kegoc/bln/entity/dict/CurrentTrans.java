package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class CurrentTrans implements HasId, HasCode, HasName {
	private Long id;
	
	@NotNull @Size(max = 15)
	private String code;

	@NotNull @Size(max = 100)
	private String name;
	
	@NotNull @Size(max = 100)
	private String manufacturer;
	
	@NotNull
	private Company company;
	
	private String serialNumber;
	private Double ratedCurrent1;
	private Double ratedCurrent2;
	private Double accuracyClass;
	private Double minCurrent;
	private Double maxCurrent;
	private LocalDate lastVerificationDate;
	private LocalDate nextVerificationDate;
	private LocalDate installationDate;
	private LocalDate disassemblyDate;
}
