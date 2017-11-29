package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.common.Lang;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.VoltageTransType;
import kz.kegoc.bln.entity.dict.dto.VoltageTransTypeDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.VoltageTransTypeService;
import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictVoltageTransType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class VoltageTransTypeResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		Query query = QueryImpl.builder()			
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<VoltageTransTypeDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, VoltageTransTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<VoltageTransTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		VoltageTransType entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, VoltageTransTypeDto.class))
			.build();		
	}
	

	@POST
	public Response create(VoltageTransTypeDto entityDto) {
		VoltageTransType newEntity = service.create(mapper.map(entityDto,VoltageTransType.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, VoltageTransTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, VoltageTransTypeDto entityDto ) {
		VoltageTransType newEntity = service.update(mapper.map(entityDto,VoltageTransType.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, VoltageTransTypeDto.class))
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
	private VoltageTransTypeService service;

	@Inject
	private DozerBeanMapper mapper;
}
