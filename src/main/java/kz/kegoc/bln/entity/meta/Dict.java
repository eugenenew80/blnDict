package kz.kegoc.bln.entity.meta;

import javax.persistence.*;
import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.HasCode;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasName;
import lombok.*;

@NamedQueries({
	@NamedQuery(name= "Dict.findAll",    query="select t from Dict t order by t.id"),
	@NamedQuery(name= "Dict.findByName", query="select t from Dict t where t.name=:name"),
	@NamedQuery(name= "Dict.findByCode", query="select t from Dict t where t.code=:code")
})

@Entity
@Data
@EqualsAndHashCode(of= {"id"})
public class Dict implements HasId, HasCode, HasName {
	private Long id;
	
	@NotNull @Size(max = 15)
	private String code;
	
	@NotNull @Size(max = 100)
	private String name;
	
	@NotNull @Size(max = 100)
	private String shortName;
	
	@NotNull @Size(max = 100)
	private String imagePath;
}
