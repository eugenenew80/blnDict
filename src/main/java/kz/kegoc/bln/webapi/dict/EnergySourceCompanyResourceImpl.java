package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import kz.kegoc.bln.entity.dict.EnergySourceCompany;
import kz.kegoc.bln.service.dict.EnergySourceCompanyService;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.dto.EnergySourceCompanyDto;
import kz.kegoc.bln.service.dict.EnergySourceService;


@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EnergySourceCompanyResourceImpl {

	@GET
	public Response getAll(@PathParam("energySourceId") Long energySourceId) {
		List<EnergySourceCompanyDto> list = energySourceService.findById(energySourceId)
			.getCompanies()
			.stream()
			.map( it-> mapper.map(it, EnergySourceCompanyDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<EnergySourceCompanyDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id) {
		EnergySourceCompany entity = service.findById(id);
		return Response.ok()
				.entity(mapper.map(entity, EnergySourceCompanyDto.class))
				.build();
	}


	@POST
	public Response create(EnergySourceCompanyDto entityDto) {
		EnergySourceCompany newEntity = service.create(mapper.map(entityDto, EnergySourceCompany.class));
		return Response.ok()
				.entity(mapper.map(newEntity, EnergySourceCompanyDto.class))
				.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, EnergySourceCompanyDto entityDto ) {
		EnergySourceCompany newEntity = service.update(mapper.map(entityDto, EnergySourceCompany.class));
		return Response.ok()
				.entity(mapper.map(newEntity, EnergySourceCompanyDto.class))
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
	private DozerBeanMapper mapper;}
