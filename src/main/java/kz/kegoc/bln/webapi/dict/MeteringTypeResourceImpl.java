package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.common.Lang;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.MeteringType;
import kz.kegoc.bln.entity.dict.dto.MeteringTypeDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.MeteringTypeService;

import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictMeteringType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeteringTypeResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		Query query = QueryImpl.builder()			
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<MeteringTypeDto> list = meteringTypeService.find(query)
			.stream()
			.map( it-> mapper.map(it, MeteringTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<MeteringTypeDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		MeteringType meteringType = meteringTypeService.findById(id);
		return Response.ok()
			.entity(mapper.map(meteringType, MeteringTypeDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code, @QueryParam("lang") Lang lang) {
		MeteringType meteringType = meteringTypeService.findByCode(code);
		return Response.ok()
			.entity(mapper.map(meteringType, MeteringTypeDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name, @QueryParam("lang") Lang lang) {
		MeteringType meteringType = meteringTypeService.findByName(name);
		return Response.ok()
			.entity(mapper.map(meteringType, MeteringTypeDto.class))
			.build();
	}

	
	@POST
	public Response create(MeteringTypeDto meteringTypeDto) {
		MeteringType newMeteringType = meteringTypeService.create(mapper.map(meteringTypeDto, MeteringType.class));	
		return Response.ok()
			.entity(mapper.map(newMeteringType, MeteringTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MeteringTypeDto meteringTypeDto ) {
		MeteringType newMeteringType = meteringTypeService.update(mapper.map(meteringTypeDto, MeteringType.class)); 
		return Response.ok()
			.entity(mapper.map(newMeteringType, MeteringTypeDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		meteringTypeService.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject
	private MeteringTypeService meteringTypeService;

	@Inject
	private DozerBeanMapper mapper;
}
