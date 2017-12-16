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
import kz.kegoc.bln.entity.dict.MeteringType;
import kz.kegoc.bln.entity.dict.dto.MeteringTypeDto;
import kz.kegoc.bln.service.dict.MeteringTypeService;


@Stateless
@Path("/dict/dictMeteringType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeteringTypeResourceImpl {

	@GET 
	public Response getAll(
		@QueryParam("name") String name,
		@QueryParam("lang") Lang lang
	) {
		List<MeteringTypeDto> list = service.find(null, null, name, buildSessionContext(lang))
			.stream()
			.map( it-> mapper.map(it, MeteringTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<MeteringTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		MeteringType meteringType = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(meteringType, MeteringTypeDto.class))
			.build();		
	}
	

	@POST
	public Response create(MeteringTypeDto entityDto) {
		MeteringType entity = mapper.map(entityDto, MeteringType.class);
		MeteringType newMeteringType = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newMeteringType, MeteringTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MeteringTypeDto entityDto ) {
		MeteringType entity = mapper.map(entityDto, MeteringType.class);
		MeteringType newMeteringType = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newMeteringType, MeteringTypeDto.class))
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
	private MeteringTypeService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
