package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;
import kz.kegoc.bln.entity.dict.EnergyZone;
import kz.kegoc.bln.entity.dict.dto.EnergyZoneDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.EnergyZoneService;


@RequestScoped
@Path("/dict/dictEnergyZone")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EnergyZoneResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<EnergyZoneDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, EnergyZoneDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<EnergyZoneDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		EnergyZone entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, EnergyZoneDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		EnergyZone entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, EnergyZoneDto.class))
			.build(); 
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		EnergyZone entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, EnergyZoneDto.class))
			.build();
	}

	
	@POST
	public Response create(EnergyZoneDto entityDto) {
		EnergyZone newEntity = service.create(mapper.map(entityDto,EnergyZone.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, EnergyZoneDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, EnergyZoneDto entityDto ) {
		EnergyZone newEntity = service.update(mapper.map(entityDto,EnergyZone.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, EnergyZoneDto.class))
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
	private EnergyZoneService service;

	@Inject
	private DozerBeanMapper mapper;}
