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
import kz.kegoc.bln.entity.dict.EnergySourceType;
import kz.kegoc.bln.entity.dict.dto.EnergySourceTypeDto;
import kz.kegoc.bln.service.dict.EnergySourceTypeService;

@Stateless
@Path("/dict/dictEnergySourceType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EnergySourceTypeResourceImpl {

	@GET 
	public Response getAll(
		@QueryParam("shortName") String shortName,
		@QueryParam("name") String name,
		@QueryParam("lang") Lang lang
	) {
		List<EnergySourceTypeDto> list = service.find(null, shortName, name, buildSessionContext(lang))
			.stream()
			.map( it-> mapper.getMapper().map(it, EnergySourceTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<EnergySourceTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		EnergySourceType entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, EnergySourceTypeDto.class))
			.build();		
	}
	

	@POST
	public Response create(EnergySourceTypeDto entityDto) {
		EnergySourceType entity = mapper.getMapper().map(entityDto, EnergySourceType.class);
		EnergySourceType newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, EnergySourceTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, EnergySourceTypeDto entityDto ) {
		EnergySourceType entity = mapper.getMapper().map(entityDto, EnergySourceType.class);
		EnergySourceType newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, EnergySourceTypeDto.class))
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
	private EnergySourceTypeService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
