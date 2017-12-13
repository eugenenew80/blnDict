package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.EnergyZone;
import kz.kegoc.bln.entity.dict.dto.EnergyZoneDto;
import kz.kegoc.bln.service.dict.EnergyZoneService;

@Stateless
@Path("/dict/dictEnergyZone")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EnergyZoneResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		List<EnergyZoneDto> list = service.findAll(buildSessionContext(lang))
			.stream()
			.map( it-> mapper.map(it, EnergyZoneDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<EnergyZoneDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		EnergyZone entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, EnergyZoneDto.class))
			.build();		
	}
	

	@POST
	public Response create(EnergyZoneDto entityDto) {
		EnergyZone entity = mapper.map(entityDto, EnergyZone.class);
		EnergyZone newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newEntity, EnergyZoneDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, EnergyZoneDto entityDto ) {
		EnergyZone entity = mapper.map(entityDto, EnergyZone.class);
		EnergyZone newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newEntity, EnergyZoneDto.class))
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
	private EnergyZoneService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
