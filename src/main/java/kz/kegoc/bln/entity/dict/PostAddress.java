package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class PostAddress implements HasId, HasName {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 10)
	private String index;

	@NotNull
	private Country country;
	@NotNull
	private Region region;

	@NotNull @Size(max = 150)
	private String locality;

	@NotNull @Size(max = 150)
	private String street;

	@NotNull @Size(max = 50)
	private String houseNumber;

	@Size(max = 50)
	private String buildingNumber;

	@Size(max = 50)
	private String apartment;

	@Size(max = 50)
	private String officeNumber;
}
