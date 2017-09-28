package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;
import kz.kegoc.bln.entity.dict.Company;
import kz.kegoc.bln.entity.dict.dto.CompanyDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.CompanyService;


@RequestScoped
@Path("/dict/dictCompany")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class CompanyResourceImpl {
	
	public CompanyResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/dict/CompanyDtoDefaultMapping.xml"));
	} 

	
	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<CompanyDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, CompanyDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<CompanyDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		Company entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, CompanyDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		Company entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, CompanyDto.class))
			.build(); 
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		Company entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, CompanyDto.class))
			.build();
	}

	
	@POST
	public Response create(CompanyDto entityDto) {
		Company newEntity = service.create(mapper.map(entityDto,Company.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, CompanyDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, CompanyDto entityDto ) {
		Company newEntity = service.update(mapper.map(entityDto,Company.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, CompanyDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject private CompanyService service;
	private DozerBeanMapper mapper;
}
