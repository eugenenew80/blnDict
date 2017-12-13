package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointCharacteristic;
import kz.kegoc.bln.entity.dict.dto.MeteringPointCharacteristicDto;
import kz.kegoc.bln.service.dict.MeteringPointCharacteristicService;
import kz.kegoc.bln.service.dict.MeteringPointService;
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
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeteringPointCharacteristicResourceImpl {

	@GET
	public Response getAll(@PathParam("meteringPointId") Long meteringPointId, @QueryParam("lang") Lang lang) {
		List<MeteringPointCharacteristicDto> list = meteringPointService.findById(meteringPointId, buildSessionContext(lang))
			.getCharacteristics()
			.stream()
			.map( it-> mapper.map(it, MeteringPointCharacteristicDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<MeteringPointCharacteristicDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		MeteringPointCharacteristic entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, MeteringPointCharacteristicDto.class))
			.build();
	}


	@POST
	public Response create(MeteringPointCharacteristicDto entityDto) {
		MeteringPointCharacteristic entity = mapper.map(entityDto, MeteringPointCharacteristic.class);
		MeteringPointCharacteristic newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointCharacteristicDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, MeteringPointCharacteristicDto entityDto ) {
		MeteringPointCharacteristic entity = mapper.map(entityDto, MeteringPointCharacteristic.class);
		MeteringPointCharacteristic newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointCharacteristicDto.class))
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
	private MeteringPointService meteringPointService;

	@Inject
	private MeteringPointCharacteristicService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
