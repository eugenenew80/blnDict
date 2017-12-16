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
import kz.kegoc.bln.entity.dict.MeterType;
import kz.kegoc.bln.entity.dict.dto.MeterTypeDto;
import kz.kegoc.bln.service.dict.MeterTypeService;

@Stateless
@Path("/dict/dictMeterType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeterTypeResourceImpl {

	@GET
	public Response getAll(
		@QueryParam("name") String name,
		@QueryParam("lang") Lang lang
	) {
		List<MeterTypeDto> list = service.find(null, null, name, buildSessionContext(lang))
			.stream()
			.map(it-> mapper.map(it, MeterTypeDto.class))
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<MeterTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		MeterType meterType = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(meterType, MeterTypeDto.class))
			.build();		
	}
	

	@POST
	public Response create(MeterTypeDto entityDto) {
		MeterType entity = mapper.map(entityDto, MeterType.class);
		MeterType newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newEntity, MeterTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MeterTypeDto entityDto ) {
		MeterType entity = mapper.map(entityDto, MeterType.class);
		MeterType newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newEntity, MeterTypeDto.class))
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
	private MeterTypeService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
