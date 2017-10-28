package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.CurrentTransType;
import kz.kegoc.bln.entity.dict.dto.CurrentTransTypeDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.CurrentTransTypeService;

import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictCurrentTransType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class CurrentTransTypeResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<CurrentTransTypeDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, CurrentTransTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<CurrentTransTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		CurrentTransType entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, CurrentTransTypeDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		CurrentTransType entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, CurrentTransTypeDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		CurrentTransType entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, CurrentTransTypeDto.class))
			.build();
	}

	
	@POST
	public Response create(CurrentTransTypeDto entityDto) {
		CurrentTransType newEntity = service.create(mapper.map(entityDto,CurrentTransType.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, CurrentTransTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id,CurrentTransTypeDto entityDto ) {
		CurrentTransType newEntity = service.update(mapper.map(entityDto,CurrentTransType.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, CurrentTransTypeDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject
	private CurrentTransTypeService service;

	@Inject
	private DozerBeanMapper mapper;

	@Context
	private SecurityContext context;
}
