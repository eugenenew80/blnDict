package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.dict.SubstationMeteringPoint;
import kz.kegoc.bln.entity.dict.dto.SubstationMeteringPointDto;
import kz.kegoc.bln.service.dict.MeteringPointService;
import kz.kegoc.bln.service.dict.SubstationMeteringPointService;
import kz.kegoc.bln.service.dict.SubstationService;
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
public class SubstationMeteringPointResourceImpl {

	@GET
	public Response getAll(@PathParam("substationId") Long substationId) {
		List<SubstationMeteringPointDto> list = substationService.findById(substationId)
			.getMeteringPoints()
			.stream()
			.map( it-> mapper.map(it, SubstationMeteringPointDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<SubstationMeteringPointDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id) {
		SubstationMeteringPoint entity = service.findById(id);
		return Response.ok()
				.entity(mapper.map(entity, SubstationMeteringPointDto.class))
				.build();
	}


	@POST
	public Response create(SubstationMeteringPointDto entityDto) {
        entityDto.setMeteringPointName(meteringPointService.findById(entityDto.getMeteringPointId()).getName());
		SubstationMeteringPoint newEntity = service.create(mapper.map(entityDto, SubstationMeteringPoint.class));
		return Response.ok()
				.entity(mapper.map(newEntity, SubstationMeteringPointDto.class))
				.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, SubstationMeteringPointDto entityDto ) {
        entityDto.setMeteringPointName(meteringPointService.findById(entityDto.getMeteringPointId()).getName());
		SubstationMeteringPoint newEntity = service.update(mapper.map(entityDto, SubstationMeteringPoint.class));
		return Response.ok()
				.entity(mapper.map(newEntity, SubstationMeteringPointDto.class))
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
	private SubstationService substationService;

	@Inject
	private MeteringPointService meteringPointService;

	@Inject
	private SubstationMeteringPointService service;

	@Inject
	private DozerBeanMapper mapper;

	@Context
	private SecurityContext context;
}
