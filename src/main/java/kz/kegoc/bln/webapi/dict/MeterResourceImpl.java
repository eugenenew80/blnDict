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
import kz.kegoc.bln.entity.dict.Meter;
import kz.kegoc.bln.entity.dict.dto.MeterDto;
import kz.kegoc.bln.service.dict.MeterService;

@Stateless
@Path("/dict/dictMeter")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeterResourceImpl {

	@GET 
	public Response getAll(
		@QueryParam("name") String name,
		@QueryParam("lang") Lang lang,
		@QueryParam("serialNumber") String serialNumber
	) {
		List<MeterDto> list = service.find(null, null, name, serialNumber, buildSessionContext(lang))
			.stream()
			.map(it-> mapper.getMapper().map(it, MeterDto.class))
			.collect(Collectors.toList());

		return Response.ok()
			.entity(new GenericEntity<Collection<MeterDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		Meter entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, MeterDto.class))
			.build();		
	}

	
	@POST
	public Response create(MeterDto entityDto) {
		Meter entity = mapper.getMapper().map(entityDto, Meter.class);
		Meter newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, MeterDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MeterDto entityDto ) {
		Meter entity = mapper.getMapper().map(entityDto, Meter.class);
		Meter newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, MeterDto.class))
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
	private MeterService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
