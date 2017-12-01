package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.dict.ContactEmail;
import kz.kegoc.bln.entity.dict.dto.ContactEmailDto;
import kz.kegoc.bln.service.dict.ContactEmailService;
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
public class ContactEmailResourceImpl {

	@GET
	public Response getAll(@PathParam("contactId") Long contactId) {
		List<ContactEmailDto> list = contactService.findById(contactId)
			.getContactEmails()
			.stream()
			.map( it-> mapper.map(it, ContactEmailDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<ContactEmailDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id) {
		ContactEmail entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, ContactEmailDto.class))
			.build();
	}


	@POST
	public Response create(ContactEmailDto entityDto) {
		ContactEmail newEntity = service.create(mapper.map(entityDto, ContactEmail.class));
		return Response.ok()
			.entity(mapper.map(newEntity, ContactEmailDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, ContactEmailDto entityDto ) {
		ContactEmail newEntity = service.update(mapper.map(entityDto, ContactEmail.class));
		return Response.ok()
			.entity(mapper.map(newEntity, ContactEmailDto.class))
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
	private ContactEmailService service;

	@Inject
	private DozerBeanMapper mapper;
}
