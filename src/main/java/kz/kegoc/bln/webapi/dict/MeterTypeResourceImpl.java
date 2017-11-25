package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.common.Lang;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.MeterType;
import kz.kegoc.bln.entity.dict.dto.MeterTypeDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.MeterTypeService;

import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictMeterType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeterTypeResourceImpl {

	@GET
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		Query query = QueryImpl.builder()			
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<MeterTypeDto> list = meterTypeService.find(query)
			.stream()
			.map( it-> mapper.map(it, MeterTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<MeterTypeDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		MeterType meterType = meterTypeService.findById(id);
		return Response.ok()
			.entity(mapper.map(meterType, MeterTypeDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code, @QueryParam("lang") Lang lang) {
		MeterType meterType = meterTypeService.findByCode(code);
		return Response.ok()
			.entity(mapper.map(meterType, MeterTypeDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name, @QueryParam("lang") Lang lang) {
		MeterType meterType = meterTypeService.findByName(name);
		return Response.ok()
			.entity(mapper.map(meterType, MeterTypeDto.class))
			.build();
	}

	
	@POST
	public Response create(MeterTypeDto meterTypeDto) {
		MeterType newMeterType = meterTypeService.create(mapper.map(meterTypeDto, MeterType.class));	
		return Response.ok()
			.entity(mapper.map(newMeterType, MeterTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MeterTypeDto meterTypeDto ) {
		MeterType newMeterType = meterTypeService.update(mapper.map(meterTypeDto, MeterType.class)); 
		return Response.ok()
			.entity(mapper.map(newMeterType, MeterTypeDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		meterTypeService.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject
	private MeterTypeService meterTypeService;

	@Inject
	private DozerBeanMapper mapper;
}
