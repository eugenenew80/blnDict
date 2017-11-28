package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import kz.kegoc.bln.entity.dict.EnergySourceBusinessPartner;
import kz.kegoc.bln.service.dict.EnergySourceCompanyService;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.dto.EnergySourceBusinessPartnerDto;
import kz.kegoc.bln.service.dict.EnergySourceService;

@Stateless
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EnergySourceBusinessPartnerResourceImpl {

	@GET
	public Response getAll(@PathParam("energySourceId") Long energySourceId) {
		List<EnergySourceBusinessPartnerDto> list = energySourceService.findById(energySourceId)
			.getBusinessPartners()
			.stream()
			.map( it-> mapper.map(it, EnergySourceBusinessPartnerDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<EnergySourceBusinessPartnerDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id) {
		EnergySourceBusinessPartner entity = service.findById(id);
		return Response.ok()
				.entity(mapper.map(entity, EnergySourceBusinessPartnerDto.class))
				.build();
	}


	@POST
	public Response create(EnergySourceBusinessPartnerDto entityDto) {
		EnergySourceBusinessPartner newEntity = service.create(mapper.map(entityDto, EnergySourceBusinessPartner.class));
		return Response.ok()
				.entity(mapper.map(newEntity, EnergySourceBusinessPartnerDto.class))
				.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, EnergySourceBusinessPartnerDto entityDto ) {
		EnergySourceBusinessPartner newEntity = service.update(mapper.map(entityDto, EnergySourceBusinessPartner.class));
		return Response.ok()
				.entity(mapper.map(newEntity, EnergySourceBusinessPartnerDto.class))
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
	private EnergySourceCompanyService service;

	@Inject
	private DozerBeanMapper mapper;
}
