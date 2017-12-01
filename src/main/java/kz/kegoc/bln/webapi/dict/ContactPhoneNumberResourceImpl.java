package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.dict.ContactPhoneNumber;
import kz.kegoc.bln.entity.dict.dto.ContactPhoneNumberDto;
import kz.kegoc.bln.service.dict.ContactPhoneNumberService;
import kz.kegoc.bln.service.dict.ContactService;
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
public class ContactPhoneNumberResourceImpl {

	@GET
	public Response getAll(@PathParam("contactId") Long contactId) {
		List<ContactPhoneNumberDto> list = contactService.findById(contactId)
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
	public Response getById(@PathParam("id") Long id) {
		ContactPhoneNumber entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, ContactPhoneNumberDto.class))
			.build();
	}


	@POST
	public Response create(ContactPhoneNumberDto entityDto) {
		ContactPhoneNumber newEntity = service.create(mapper.map(entityDto, ContactPhoneNumber.class));
		return Response.ok()
			.entity(mapper.map(newEntity, ContactPhoneNumberDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, ContactPhoneNumberDto entityDto ) {
		ContactPhoneNumber newEntity = service.update(mapper.map(entityDto, ContactPhoneNumber.class));
		return Response.ok()
			.entity(mapper.map(newEntity, ContactPhoneNumberDto.class))
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
	private ContactService contactService;

	@Inject
	private ContactPhoneNumberService service;

	@Inject
	private DozerBeanMapper mapper;
}
