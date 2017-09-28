package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;
import kz.kegoc.bln.entity.dict.EnergySourceType;
import kz.kegoc.bln.entity.dict.dto.EnergySourceTypeDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.EnergySourceTypeService;


@RequestScoped
@Path("/dict/dictEnergySourceType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EnergySourceTypeResourceImpl {
	
	public EnergySourceTypeResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/dict/EnergySourceTypeDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<EnergySourceTypeDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, EnergySourceTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<EnergySourceTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		EnergySourceType entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, EnergySourceTypeDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		EnergySourceType entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, EnergySourceTypeDto.class))
			.build(); 
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		EnergySourceType entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, EnergySourceTypeDto.class))
			.build();
	}

	
	@POST
	public Response create(EnergySourceTypeDto entityDto) {
		EnergySourceType newEntity = service.create(mapper.map(entityDto,EnergySourceType.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, EnergySourceTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, EnergySourceTypeDto entityDto ) {
		EnergySourceType newEntity = service.update(mapper.map(entityDto,EnergySourceType.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, EnergySourceTypeDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject private EnergySourceTypeService service;
	private DozerBeanMapper mapper;
}
