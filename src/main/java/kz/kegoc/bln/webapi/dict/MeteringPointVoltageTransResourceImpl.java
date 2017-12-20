package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointVoltageTrans;
import kz.kegoc.bln.entity.dict.dto.MeteringPointVoltageTransDto;
import kz.kegoc.bln.service.dict.MeteringPointVoltageTransService;
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
public class MeteringPointVoltageTransResourceImpl {

	@GET
	public Response getAll(@PathParam("meteringPointId") Long meteringPointId, @QueryParam("lang") Lang lang) {
		List<MeteringPointVoltageTransDto> list = meteringPointService.findById(meteringPointId, buildSessionContext(lang))
			.getVoltageTrans()
			.stream()
			.map( it-> mapper.getMapper().map(it, MeteringPointVoltageTransDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<MeteringPointVoltageTransDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		MeteringPointVoltageTrans entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, MeteringPointVoltageTransDto.class))
			.build();
	}


	@POST
	public Response create(MeteringPointVoltageTransDto entityDto) {
		MeteringPointVoltageTrans entity = mapper.getMapper().map(entityDto, MeteringPointVoltageTrans.class);
		MeteringPointVoltageTrans newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, MeteringPointVoltageTransDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, MeteringPointVoltageTransDto entityDto ) {
		MeteringPointVoltageTrans entity = mapper.getMapper().map(entityDto, MeteringPointVoltageTrans.class);
		MeteringPointVoltageTrans newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, MeteringPointVoltageTransDto.class))
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
	private MeteringPointVoltageTransService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
