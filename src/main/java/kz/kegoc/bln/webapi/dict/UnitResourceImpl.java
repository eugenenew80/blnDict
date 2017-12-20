package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.Unit;
import kz.kegoc.bln.entity.dict.dto.UnitDto;
import kz.kegoc.bln.service.dict.UnitService;

@Stateless
@Path("/dict/dictUnit")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class UnitResourceImpl {

	@GET 
	public Response getAll(
		@QueryParam("code") String code,
		@QueryParam("name") String name,
		@QueryParam("lang") Lang lang
	) {
		List<UnitDto> list = service.find(code, null, name, buildSessionContext(lang))
			.stream()
			.map( it-> mapper.getMapper().map(it, UnitDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<UnitDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		Unit unit = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(unit, UnitDto.class))
			.build();		
	}

	
	@POST
	public Response create(UnitDto entityDto) {
		Unit entity = mapper.getMapper().map(entityDto, Unit.class);
		Unit newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, UnitDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, UnitDto entityDto ) {
		Unit entity = mapper.getMapper().map(entityDto, Unit.class);
		Unit newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, UnitDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id, buildSessionContext(null));
		return Response.noContent()
			.build();
	}


	private SessionContext buildSessionContext(Lang lang) {
		SessionContext context = new SessionContext();
		context.setLang(lang!=null ? lang : defLang);
		context.setUser(((CustomPrincipal)securityContext.getUserPrincipal()).getUser());
		return context;
	}


	@Inject
	private UnitService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
