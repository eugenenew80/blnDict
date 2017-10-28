package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.Meter;
import kz.kegoc.bln.entity.dict.dto.MeterDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.MeterService;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Stateless
@Path("/dict/dictMeter")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeterResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<MeterDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, MeterDto.class) )
			.collect(Collectors.toList());

		return Response.ok()
			.entity(new GenericEntity<Collection<MeterDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		Meter entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, MeterDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		Meter entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, MeterDto.class))
			.build(); 
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		Meter entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, MeterDto.class))
			.build();
	}

	
	@POST
	public Response create(MeterDto entityDto) {
		Meter newEntity = service.create(mapper.map(entityDto, Meter.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, MeterDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MeterDto entityDto ) {
		Meter newEntity = service.update(mapper.map(entityDto, Meter.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, MeterDto.class))
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
	private MeterService service;

	@Inject
	private DozerBeanMapper mapper;

	@Context
	private SecurityContext context;
}
