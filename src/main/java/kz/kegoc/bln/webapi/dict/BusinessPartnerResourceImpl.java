package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.entity.dict.dto.BusinessPartnerDto;
import kz.kegoc.bln.service.dict.BusinessPartnerService;
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
@Path("/dict/dictBusinessPartner")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BusinessPartnerResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @HeaderParam("lang") Lang lang) {
		List<BusinessPartnerDto> list = service.findAll(buildSessionContext(lang))
			.stream()
			.map(it-> mapper.map(it, BusinessPartnerDto.class))
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<BusinessPartnerDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		BusinessPartner entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, BusinessPartnerDto.class))
			.build();		
	}
	

	@POST
	public Response create(BusinessPartnerDto entityDto, @HeaderParam("lang") Lang lang) {
		BusinessPartner entity = mapper.map(entityDto, BusinessPartner.class);
		BusinessPartner newEntity = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, BusinessPartnerDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, BusinessPartnerDto entityDto, @HeaderParam("lang") Lang lang) {
		BusinessPartner entity = mapper.map(entityDto, BusinessPartner.class);
		BusinessPartner newEntity = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, BusinessPartnerDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		service.delete(id, buildSessionContext(lang));
		return Response.noContent()
			.build();
	}


	@Path("/{businessPartnerId : \\d+}/dictBusinessPartnerContact")
	public ContactResourceImpl getContactResource() {
		return contactResource;
	}


	private SessionContext buildSessionContext(Lang lang) {
		SessionContext context = new SessionContext();
		context.setLang(lang!=null ? lang : defLang);
		context.setUser(((CustomPrincipal)securityContext.getUserPrincipal()).getUser());
		return context;
	}


	@Inject
	private ContactResourceImpl contactResource;

	@Inject
	private BusinessPartnerService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
