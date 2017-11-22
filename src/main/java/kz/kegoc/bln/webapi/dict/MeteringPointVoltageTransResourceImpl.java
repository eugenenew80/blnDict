package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.dict.MeteringPointVoltageTrans;
import kz.kegoc.bln.entity.dict.dto.MeteringPointVoltageTransDto;
import kz.kegoc.bln.service.dict.MeteringPointVoltageTransService;
import kz.kegoc.bln.service.dict.MeteringPointService;
import org.dozer.DozerBeanMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeteringPointVoltageTransResourceImpl {

	@GET
	public Response getAll(@PathParam("meteringPointId") Long meteringPointId) {
		List<MeteringPointVoltageTransDto> list = meteringPointService.findById(meteringPointId)
			.getMeters()
			.stream()
			.map( it-> mapper.map(it, MeteringPointVoltageTransDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<MeteringPointVoltageTransDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id) {
		MeteringPointVoltageTrans entity = service.findById(id);
		return Response.ok()
				.entity(mapper.map(entity, MeteringPointVoltageTransDto.class))
				.build();
	}


	@POST
	public Response create(MeteringPointVoltageTransDto entityDto) {
		MeteringPointVoltageTrans newEntity = service.create(mapper.map(entityDto, MeteringPointVoltageTrans.class));
		return Response.ok()
				.entity(mapper.map(newEntity, MeteringPointVoltageTransDto.class))
				.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, MeteringPointVoltageTransDto entityDto ) {
		MeteringPointVoltageTrans newEntity = service.update(mapper.map(entityDto, MeteringPointVoltageTrans.class));
		return Response.ok()
				.entity(mapper.map(newEntity, MeteringPointVoltageTransDto.class))
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
	private MeteringPointVoltageTransService service;

	@Inject
	private DozerBeanMapper mapper;
}
