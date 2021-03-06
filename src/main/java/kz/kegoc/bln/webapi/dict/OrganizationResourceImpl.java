package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.dto.OrganizationDto;
import kz.kegoc.bln.service.dict.OrganizationService;

@Stateless
@Path("/dict/dictOrganization")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class OrganizationResourceImpl {

	@GET 
	public Response getAll(
		@QueryParam("searchValue") String searchValue,
		@QueryParam("shortName") String shortName,
		@QueryParam("name") String name,
		@QueryParam("lang") Lang lang
	) {
		if (StringUtils.isNotEmpty(searchValue))
			shortName = searchValue;

		List<OrganizationDto> list = service.find(null, shortName, name, buildSessionContext(lang))
			.stream()
			.map(it-> mapper.getMapper().map(it, OrganizationDto.class))
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<OrganizationDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		Organization entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, OrganizationDto.class))
			.build();		
	}
	

	@POST
	public Response create(OrganizationDto entityDto) {
		Organization entity = mapper.getMapper().map(entityDto, Organization.class);
		Organization newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, OrganizationDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, OrganizationDto entityDto ) {
		Organization entity = mapper.getMapper().map(entityDto, Organization.class);
		Organization newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, OrganizationDto.class))
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
	private OrganizationService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
