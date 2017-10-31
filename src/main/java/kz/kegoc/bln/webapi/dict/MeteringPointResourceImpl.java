package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.entity.dict.dto.MeteringPointDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.MeteringPointService;

import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictMeteringPoint")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeteringPointResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<MeteringPointDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, MeteringPointDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<MeteringPointDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		MeteringPoint entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, MeteringPointDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		MeteringPoint entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, MeteringPointDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		MeteringPoint entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, MeteringPointDto.class))
			.build();
	}

	
	@POST
	public Response create(MeteringPointDto entityDto) {
		MeteringPoint newEntity = service.create(mapper.map(entityDto, MeteringPoint.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MeteringPointDto entityDto ) {
		MeteringPoint newEntity = service.update(mapper.map(entityDto, MeteringPoint.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Path("/{meteringPointId : \\d+}/dictMeteringPointMeter")
	public MeteringPointMeterResourceImpl getModules(@PathParam("meteringPointId") Long id) {
		return meteringPointMeterResourceImpl;
	}
	
	
	@Inject
	private MeteringPointService service;

	@Inject
	private MeteringPointMeterResourceImpl meteringPointMeterResourceImpl;

	@Inject
	private DozerBeanMapper mapper;

	@Context
	private SecurityContext context;
}
