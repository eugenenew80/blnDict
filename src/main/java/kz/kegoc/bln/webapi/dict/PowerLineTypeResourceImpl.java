package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLineType;
import kz.kegoc.bln.entity.dict.dto.PowerLineTypeDto;
import kz.kegoc.bln.service.dict.PowerLineTypeService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@Path("/dict/dictPowerLineType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class PowerLineTypeResourceImpl {
	private static final Logger logger = LoggerFactory.getLogger(PowerLineTypeResourceImpl.class);

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @HeaderParam("lang") Lang lang) {
		List<PowerLineTypeDto> list = service.findAll(buildSessionContext(lang))
			.stream()
			.map( it-> mapper.map(it, PowerLineTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<PowerLineTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		PowerLineType entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, PowerLineTypeDto.class))
			.build();		
	}


	@POST
	public Response create(PowerLineTypeDto entityDto, @HeaderParam("lang") Lang lang) {
		PowerLineType entity = mapper.map(entityDto, PowerLineType.class);
		PowerLineType newEntity = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, PowerLineTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, PowerLineTypeDto entityDto, @HeaderParam("lang") Lang lang) {
		PowerLineType entity = mapper.map(entityDto, PowerLineType.class);
		PowerLineType newEntity = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, PowerLineTypeDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		service.delete(id, buildSessionContext(lang));
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
	private PowerLineTypeService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
