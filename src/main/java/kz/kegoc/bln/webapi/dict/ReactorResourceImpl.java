package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Reactor;
import kz.kegoc.bln.entity.dict.dto.ReactorDto;
import kz.kegoc.bln.service.dict.ReactorService;
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
@Path("/dict/dictReactor")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ReactorResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		List<ReactorDto> list = service.findAll(buildSessionContext(lang))
			.stream()
			.map( it-> mapper.map(it, ReactorDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<ReactorDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		Reactor entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, ReactorDto.class))
			.build();		
	}


	@POST
	public Response create(ReactorDto entityDto) {
		Reactor entity = mapper.map(entityDto, Reactor.class);
		Reactor newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));
		
		return Response.ok()
			.entity(mapper.map(newEntity, ReactorDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, ReactorDto entityDto ) {
		Reactor entity = mapper.map(entityDto, Reactor.class);
		Reactor newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));
		
		return Response.ok()
			.entity(mapper.map(newEntity, ReactorDto.class))
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
	private ReactorService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
