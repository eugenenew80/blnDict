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
import kz.kegoc.bln.entity.dict.CurrentTransType;
import kz.kegoc.bln.entity.dict.dto.CurrentTransTypeDto;
import kz.kegoc.bln.service.dict.CurrentTransTypeService;

@Stateless
@Path("/dict/dictCurrentTransType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class CurrentTransTypeResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @HeaderParam("lang") Lang lang) {
		List<CurrentTransTypeDto> list = service.findAll(buildSessionContext(lang))
			.stream()
			.map(it-> mapper.map(it, CurrentTransTypeDto.class))
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<CurrentTransTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		CurrentTransType entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, CurrentTransTypeDto.class))
			.build();		
	}
	

	@POST
	public Response create(CurrentTransTypeDto entityDto, @HeaderParam("lang") Lang lang) {
		CurrentTransType entity = mapper.map(entityDto, CurrentTransType.class);
		CurrentTransType newEntity = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, CurrentTransTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, CurrentTransTypeDto entityDto, @HeaderParam("lang") Lang lang) {
		CurrentTransType entity = mapper.map(entityDto, CurrentTransType.class);
		CurrentTransType newEntity = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, CurrentTransTypeDto.class))
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
	private CurrentTransTypeService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
