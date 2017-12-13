package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.ContactPhoneNumber;
import kz.kegoc.bln.entity.dict.dto.ContactPhoneNumberDto;
import kz.kegoc.bln.service.dict.ContactPhoneNumberService;
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
public class ContactPhoneNumberResourceImpl {

	@GET
	public Response getAll(@PathParam("contactId") Long contactId, @HeaderParam("lang") Lang lang) {
		List<ContactPhoneNumberDto> list = contactService.findById(contactId, buildSessionContext(lang))
			.getContactPhoneNumbers()
			.stream()
			.map( it-> mapper.map(it, ContactPhoneNumberDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<ContactPhoneNumberDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		ContactPhoneNumber entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, ContactPhoneNumberDto.class))
			.build();
	}


	@POST
	public Response create(ContactPhoneNumberDto entityDto, @HeaderParam("lang") Lang lang) {
		ContactPhoneNumber entity = mapper.map(entityDto, ContactPhoneNumber.class);
		ContactPhoneNumber newEntity = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, ContactPhoneNumberDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, ContactPhoneNumberDto entityDto, @HeaderParam("lang") Lang lang ) {
		ContactPhoneNumber entity = mapper.map(entityDto, ContactPhoneNumber.class);
		ContactPhoneNumber newEntity = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, ContactPhoneNumberDto.class))
			.build();
	}


	@DELETE
	@Path("{id : \\d+}")
	public Response delete(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		service.delete(id, buildSessionContext(lang));
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
	private ContactService contactService;

	@Inject
	private ContactPhoneNumberService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
