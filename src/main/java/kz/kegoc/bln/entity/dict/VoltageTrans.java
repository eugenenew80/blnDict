package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class VoltageTrans implements HasId, HasCode, HasName {
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
	private Double ratedVoltage1;
	private Double ratedVoltage2;
	private Double accuracyClass;
	private Double minVoltage;
	private Double maxVoltage;
	private LocalDate lastVerificationDate;
	private LocalDate nextVerificationDate;
}
