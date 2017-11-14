package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.dict.Country;
import kz.kegoc.bln.entity.dict.dto.CountryDto;
import kz.kegoc.bln.repository.common.query.ConditionType;
import kz.kegoc.bln.repository.common.query.MyQueryParam;
import kz.kegoc.bln.repository.common.query.Query;
import kz.kegoc.bln.repository.common.query.QueryImpl;
import kz.kegoc.bln.service.dict.CountryService;
import org.dozer.DozerBeanMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Stateless
@Path("/dict/dictCountry")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class CountryResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<CountryDto> list = countryService.find(query)
			.stream()
			.map( it-> mapper.map(it, CountryDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<CountryDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		Country entity = countryService.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, CountryDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		Country entity = countryService.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, CountryDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		Country entity = countryService.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, CountryDto.class))
			.build();
	}

	
	@POST
	public Response create(CountryDto entityDto) {
		Country newEntity = countryService.create(mapper.map(entityDto, Country.class));
		return Response.ok()
			.entity(mapper.map(newEntity, CountryDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, CountryDto entityDto ) {
		Country newEntity = countryService.update(mapper.map(entityDto, Country.class));
		return Response.ok()
			.entity(mapper.map(newEntity, CountryDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		countryService.delete(id);		
		return Response.noContent()
			.build();
	}	
	

	@Inject
	private CountryService countryService;

	@Inject
	private DozerBeanMapper mapper;

	@Context
	private SecurityContext context;
}
