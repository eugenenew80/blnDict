package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.dict.SubstationBusinessPartner;
import kz.kegoc.bln.service.dict.SubstationCompanyService;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.dto.SubstationBusinessPartnerDto;
import kz.kegoc.bln.service.dict.SubstationService;

@Stateless
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class SubstationBusinessPartnerResourceImpl {

	@GET
	public Response getAll(@PathParam("substationId") Long substationId) {
		List<SubstationBusinessPartnerDto> list = substationService.findById(substationId)
			.getBusinessPartners()
			.stream()
			.map( it-> mapper.map(it, SubstationBusinessPartnerDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<SubstationBusinessPartnerDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id) {
		SubstationBusinessPartner entity = service.findById(id);
		return Response.ok()
				.entity(mapper.map(entity, SubstationBusinessPartnerDto.class))
				.build();
	}


	@POST
	public Response create(SubstationBusinessPartnerDto entityDto) {
		SubstationBusinessPartner newEntity = service.create(mapper.map(entityDto, SubstationBusinessPartner.class));
		return Response.ok()
				.entity(mapper.map(newEntity, SubstationBusinessPartnerDto.class))
				.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, SubstationBusinessPartnerDto entityDto ) {
		SubstationBusinessPartner newEntity = service.update(mapper.map(entityDto, SubstationBusinessPartner.class));
		return Response.ok()
				.entity(mapper.map(newEntity, SubstationBusinessPartnerDto.class))
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
	private SubstationCompanyService service;

	@Inject
	private DozerBeanMapper mapper;

	@Context
	private SecurityContext context;
}
