package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.common.Lang;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.SubstationType;
import kz.kegoc.bln.entity.dict.dto.SubstationTypeDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.SubstationTypeService;
import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictSubstationType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class SubstationTypeResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		Query query = QueryImpl.builder()			
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<SubstationTypeDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, SubstationTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<SubstationTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		SubstationType entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, SubstationTypeDto.class))
			.build();		
	}
	

	@POST
	public Response create(SubstationTypeDto entityDto) {
		SubstationType newEntity = service.create(mapper.map(entityDto,SubstationType.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, SubstationTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, SubstationTypeDto entityDto ) {
		SubstationType newEntity = service.update(mapper.map(entityDto,SubstationType.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, SubstationTypeDto.class))
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
	private SubstationTypeService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;
}
