package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointMeter;
import kz.kegoc.bln.service.dict.MeteringPointMeterService;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.dto.MeteringPointMeterDto;
import kz.kegoc.bln.service.dict.MeteringPointService;

@Stateless
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeteringPointMeterResourceImpl {

	@GET
	public Response getAll(@PathParam("meteringPointId") Long meteringPointId, @QueryParam("lang") Lang lang) {
		List<MeteringPointMeterDto> list = meteringPointService.findById(meteringPointId)
			.getMeters()
			.stream()
			.map( it-> mapper.map(it, MeteringPointMeterDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<MeteringPointMeterDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		MeteringPointMeter entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, MeteringPointMeterDto.class))
			.build();
	}


	@POST
	public Response create(MeteringPointMeterDto entityDto) {
		MeteringPointMeter newEntity = service.create(mapper.map(entityDto, MeteringPointMeter.class));
		return Response.ok()
				.entity(mapper.map(newEntity, MeteringPointMeterDto.class))
				.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, MeteringPointMeterDto entityDto ) {
		MeteringPointMeter newEntity = service.update(mapper.map(entityDto, MeteringPointMeter.class));
		return Response.ok()
				.entity(mapper.map(newEntity, MeteringPointMeterDto.class))
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
	private MeteringPointService meteringPointService;

	@Inject
	private MeteringPointMeterService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;
}
