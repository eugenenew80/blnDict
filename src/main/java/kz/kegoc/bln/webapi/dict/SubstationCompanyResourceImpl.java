package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.dict.SubstationCompany;
import kz.kegoc.bln.service.dict.SubstationCompanyService;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.dto.SubstationCompanyDto;
import kz.kegoc.bln.service.dict.SubstationService;


@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class SubstationCompanyResourceImpl {
		
	public SubstationCompanyResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/dict/SubstationCompanyDtoDefaultMapping.xml"));
	}


	@GET
	public Response getAll(@PathParam("substationId") Long substationId) {
		List<SubstationCompanyDto> list = substationService.findById(substationId)
			.getCompanies()
			.stream()
			.map( it-> mapper.map(it, SubstationCompanyDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<SubstationCompanyDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id) {
		SubstationCompany entity = service.findById(id);
		return Response.ok()
				.entity(mapper.map(entity, SubstationCompanyDto.class))
				.build();
	}


	@POST
	public Response create(SubstationCompanyDto entityDto) {
		SubstationCompany newEntity = service.create(mapper.map(entityDto, SubstationCompany.class));
		return Response.ok()
				.entity(mapper.map(newEntity, SubstationCompanyDto.class))
				.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, SubstationCompanyDto entityDto ) {
		SubstationCompany newEntity = service.update(mapper.map(entityDto, SubstationCompany.class));
		return Response.ok()
				.entity(mapper.map(newEntity, SubstationCompanyDto.class))
				.build();
	}


	@DELETE
	@Path("{id : \\d+}")
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);
		return Response.noContent()
				.build();
	}


	@Inject private SubstationService substationService;
	@Inject private SubstationCompanyService service;
	private DozerBeanMapper mapper;
}
