package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerTransformer;
import kz.kegoc.bln.entity.dict.dto.PowerTransformerDto;
import kz.kegoc.bln.service.dict.PowerTransformerService;
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
@Path("/dict/dictPowerTransformer")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class PowerTransformerResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		List<PowerTransformerDto> list = service.findAll(buildSessionContext(lang))
			.stream()
			.map( it-> mapper.map(it, PowerTransformerDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<PowerTransformerDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		PowerTransformer entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, PowerTransformerDto.class))
			.build();		
	}


	@POST
	public Response create(PowerTransformerDto entityDto) {
		PowerTransformer entity = mapper.map(entityDto, PowerTransformer.class);
		PowerTransformer newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));
		
		return Response.ok()
			.entity(mapper.map(newEntity, PowerTransformerDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, PowerTransformerDto entityDto ) {
		PowerTransformer entity = mapper.map(entityDto, PowerTransformer.class);
		PowerTransformer newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));
		
		return Response.ok()
			.entity(mapper.map(newEntity, PowerTransformerDto.class))
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
	private PowerTransformerService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
