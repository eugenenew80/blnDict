package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;
import kz.kegoc.bln.entity.dict.MeteringPointType;
import kz.kegoc.bln.entity.dict.dto.MeteringPointTypeDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.MeteringPointTypeService;


@RequestScoped
@Path("/dict/dictMeteringPointType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeteringPointTypeResourceImpl {
	
	public MeteringPointTypeResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/dict/MeteringPointTypeDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<MeteringPointTypeDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, MeteringPointTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<MeteringPointTypeDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		MeteringPointType entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, MeteringPointTypeDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		MeteringPointType entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, MeteringPointTypeDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		MeteringPointType entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, MeteringPointTypeDto.class))
			.build();
	}

	
	@POST
	public Response create(MeteringPointTypeDto entityDto) {
		MeteringPointType newEntity = service.create(mapper.map(entityDto, MeteringPointType.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MeteringPointTypeDto entityDto ) {
		MeteringPointType newEntity = service.update(mapper.map(entityDto, MeteringPointType.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointTypeDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject private MeteringPointTypeService service;
	private DozerBeanMapper mapper;
}
