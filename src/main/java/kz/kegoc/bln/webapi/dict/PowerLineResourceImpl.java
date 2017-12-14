package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.entity.dict.dto.PowerLineDto;
import kz.kegoc.bln.service.dict.PowerLineService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
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

@Stateless
@Path("/dict/dictPowerLine")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class PowerLineResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		List<PowerLineDto> list = service.findByOrg(buildSessionContext(lang))
			.stream()
			.map( it-> mapper.map(it, PowerLineDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<PowerLineDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		PowerLine entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, PowerLineDto.class))
			.build();		
	}


	@POST
	public Response create(PowerLineDto entityDto) {
		PowerLine entity = mapper.map(entityDto, PowerLine.class);
		PowerLine newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));
		
		return Response.ok()
			.entity(mapper.map(newEntity, PowerLineDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, PowerLineDto entityDto ) {
		PowerLine entity = mapper.map(entityDto, PowerLine.class);
		PowerLine newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));
		
		return Response.ok()
			.entity(mapper.map(newEntity, PowerLineDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id, buildSessionContext(null));
		return Response.noContent()
			.build();
	}


	@Path("/{powerLineId : \\d+}/dictPowerLinePart")
	public PowerLinePartResourceImpl getPowerLinePartResource() {
		return powerLinePartResource;
	}


	private SessionContext buildSessionContext(Lang lang) {
		SessionContext context = new SessionContext();
		context.setLang(lang!=null ? lang : defLang);
		context.setUser(((CustomPrincipal)securityContext.getUserPrincipal()).getUser());
		return context;
	}


	@Inject
	private PowerLinePartResourceImpl powerLinePartResource;

	@Inject
	private PowerLineService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
