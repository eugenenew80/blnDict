package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergySourceMeteringPoint;
import kz.kegoc.bln.entity.dict.dto.EnergySourceMeteringPointDto;
import kz.kegoc.bln.service.dict.EnergySourceMeteringPointService;
import kz.kegoc.bln.service.dict.EnergySourceService;
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
public class EnergySourceMeteringPointResourceImpl {
		
	@GET
	public Response getAll(@PathParam("energySourceId") Long energySourceId, @QueryParam("lang") Lang lang) {
		List<EnergySourceMeteringPointDto> list = energySourceService.findById(energySourceId, buildSessionContext(lang))
			.getMeteringPoints()
			.stream()
			.map( it-> mapper.map(it, EnergySourceMeteringPointDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<EnergySourceMeteringPointDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		EnergySourceMeteringPoint entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, EnergySourceMeteringPointDto.class))
			.build();
	}


	@POST
	public Response create(EnergySourceMeteringPointDto entityDto) {
		EnergySourceMeteringPoint entity = mapper.map(entityDto, EnergySourceMeteringPoint.class);
		EnergySourceMeteringPoint newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newEntity, EnergySourceMeteringPointDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, EnergySourceMeteringPointDto entityDto ) {
		EnergySourceMeteringPoint entity = mapper.map(entityDto, EnergySourceMeteringPoint.class);
		EnergySourceMeteringPoint newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newEntity, EnergySourceMeteringPointDto.class))
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
	private EnergySourceService energySourceService;

	@Inject
	private EnergySourceMeteringPointService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
