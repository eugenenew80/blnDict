package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;
import kz.kegoc.bln.entity.dict.EnergyNode;
import kz.kegoc.bln.entity.dict.dto.EnergyNodeDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.EnergyNodeService;


@RequestScoped
@Path("/dict/dictEnergyNode")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EnergyNodeResourceImpl {
	
	public EnergyNodeResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/dict/EnergyNodeDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<EnergyNodeDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, EnergyNodeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<EnergyNodeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		EnergyNode entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, EnergyNodeDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		EnergyNode entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, EnergyNodeDto.class))
			.build(); 
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		EnergyNode entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, EnergyNodeDto.class))
			.build();
	}

	
	@POST
	public Response create(EnergyNodeDto entityDto) {
		EnergyNode newEntity = service.create(mapper.map(entityDto,EnergyNode.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, EnergyNodeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, EnergyNodeDto entityDto ) {
		EnergyNode newEntity = service.update(mapper.map(entityDto,EnergyNode.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, EnergyNodeDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject private EnergyNodeService service;
	private DozerBeanMapper mapper;
}
