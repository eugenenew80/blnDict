package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class Meter implements HasId, HasCode, HasName {
	private Long id;
	
	@NotNull @Size(max = 15)
	private String code;
	
	@NotNull @Size(max = 100)
	private String name;
	
	@NotNull @Size(max = 100)
	private String manufacturer;

	@NotNull
	private BusinessPartner businessPartner;
	
	private String serialNumber;
	private Double ratedCurrent;
	private Double ratedVoltage;
	private Double accuracyClass;
	private Double minimumLoad;
	private Double maximumLoad;
	private LocalDate lastVerificationDate;
	private LocalDate nextVerificationDate;
}
