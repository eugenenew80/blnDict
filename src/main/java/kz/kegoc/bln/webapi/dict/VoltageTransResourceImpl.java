package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;
import kz.kegoc.bln.entity.dict.VoltageTrans;
import kz.kegoc.bln.entity.dict.dto.VoltageTransDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.VoltageTransService;


@RequestScoped
@Path("/dict/dictVoltageTrans")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class VoltageTransResourceImpl {
	
	public VoltageTransResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/dict/VoltageTransDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<VoltageTransDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, VoltageTransDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<VoltageTransDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		VoltageTrans entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, VoltageTransDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		VoltageTrans entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, VoltageTransDto.class))
			.build(); 
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		VoltageTrans entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, VoltageTransDto.class))
			.build();
	}

	
	@POST
	public Response create(VoltageTransDto entityDto) {
		VoltageTrans newEntity = service.create(mapper.map(entityDto,VoltageTrans.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, VoltageTransDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, VoltageTransDto entityDto ) {
		VoltageTrans newEntity = service.update(mapper.map(entityDto,VoltageTrans.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, VoltageTransDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject private VoltageTransService service;
	private DozerBeanMapper mapper;
}
