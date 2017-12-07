package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Contact;
import kz.kegoc.bln.entity.dict.dto.ContactDto;
import kz.kegoc.bln.service.dict.BusinessPartnerService;
import kz.kegoc.bln.service.dict.ContactService;
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
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ContactResourceImpl {

	@GET
	public Response getAll(@PathParam("businessPartnerId") Long businessPartnerId, @HeaderParam("lang") Lang lang) {
		List<ContactDto> list = businessPartnerService.findById(businessPartnerId, buildSessionContext(lang))
			.getContacts()
			.stream()
			.map( it-> mapper.map(it, ContactDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<ContactDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		Contact entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, ContactDto.class))
			.build();
	}


	@POST
	public Response create(ContactDto entityDto, @HeaderParam("lang") Lang lang) {
		Contact entity = mapper.map(entityDto, Contact.class);
		Contact newEntity = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, ContactDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, ContactDto entityDto, @HeaderParam("lang") Lang lang) {
		Contact entity = mapper.map(entityDto, Contact.class);
		Contact newEntity = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, ContactDto.class))
			.build();
	}


	@DELETE
	@Path("{id : \\d+}")
	public Response delete(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		service.delete(id, buildSessionContext(lang));
		return Response.noContent()
			.build();
	}


	@Path("/{contactId : \\d+}/dictContactEmail")
	public ContactEmailResourceImpl getContactEmailResource() {
		return contactEmailResource;
	}


	@Path("/{contactId : \\d+}/dictContactPhoneNumber")
	public ContactPhoneNumberResourceImpl getContactPhoneNumberResource() {
		return contactPhoneNumberResource;
	}


	private SessionContext buildSessionContext(Lang lang) {
		SessionContext context = new SessionContext();
		context.setLang(lang!=null ? lang : defLang);
		context.setUser(((CustomPrincipal)securityContext.getUserPrincipal()).getUser());
		return context;
	}


	@Inject
	private ContactEmailResourceImpl contactEmailResource;

	@Inject
	private ContactPhoneNumberResourceImpl contactPhoneNumberResource;

	@Inject
	private BusinessPartnerService businessPartnerService;

	@Inject
	private ContactService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
