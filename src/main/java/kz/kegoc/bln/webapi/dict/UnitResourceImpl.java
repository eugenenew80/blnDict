package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.common.Lang;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.Unit;
import kz.kegoc.bln.entity.dict.dto.UnitDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.UnitService;
import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictUnit")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class UnitResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		Query query = QueryImpl.builder()			
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<UnitDto> list = unitService.find(query)
			.stream()
			.map( it-> mapper.map(it, UnitDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<UnitDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		Unit unit = unitService.findById(id);
		return Response.ok()
			.entity(mapper.map(unit, UnitDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code, @QueryParam("lang") Lang lang) {
		Unit unit = unitService.findByCode(code);
		return Response.ok()
			.entity(mapper.map(unit, UnitDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name, @QueryParam("lang") Lang lang) {
		Unit unit = unitService.findByName(name);
		return Response.ok()
			.entity(mapper.map(unit, UnitDto.class))
			.build();
	}

	
	@POST
	public Response create(UnitDto unitDto) {
		Unit newUnit = unitService.create(mapper.map(unitDto, Unit.class));	
		return Response.ok()
			.entity(mapper.map(newUnit, UnitDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, UnitDto unitDto ) {
		Unit newUnit = unitService.update(mapper.map(unitDto, Unit.class)); 
		return Response.ok()
			.entity(mapper.map(newUnit, UnitDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		unitService.delete(id);		
		return Response.noContent()
			.build();
	}	
	

	@Inject
	private UnitService unitService;

	@Inject
	private DozerBeanMapper mapper;
}
