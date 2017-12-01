package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergySourceMeteringPoint;
import kz.kegoc.bln.entity.dict.dto.EnergySourceMeteringPointDto;
import kz.kegoc.bln.service.dict.EnergySourceMeteringPointService;
import kz.kegoc.bln.service.dict.EnergySourceService;
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
		List<EnergySourceMeteringPointDto> list = energySourceService.findById(energySourceId)
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
		EnergySourceMeteringPoint entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, EnergySourceMeteringPointDto.class))
			.build();
	}


	@POST
	public Response create(EnergySourceMeteringPointDto entityDto) {
		EnergySourceMeteringPoint newEntity = service.create(mapper.map(entityDto, EnergySourceMeteringPoint.class));
		return Response.ok()
				.entity(mapper.map(newEntity, EnergySourceMeteringPointDto.class))
				.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, EnergySourceMeteringPointDto entityDto ) {
		EnergySourceMeteringPoint newEntity = service.update(mapper.map(entityDto, EnergySourceMeteringPoint.class));
		return Response.ok()
				.entity(mapper.map(newEntity, EnergySourceMeteringPointDto.class))
				.build();
	}


	@DELETE
	@Path("{id : \\d+}")
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);
		return Response.noContent()
				.build();
	}
	
	
	@Inject
	private EnergySourceService energySourceService;

	@Inject
	private EnergySourceMeteringPointService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;
}
