package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.common.ContactType;
import kz.kegoc.bln.entity.dict.dto.ContactTypeDto;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
@Path("/dict/dictContactType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ContactTypeResourceImpl {

	@GET 
	public Response getAll(@HeaderParam("lang") Lang lang) {
		List<ContactTypeDto> list = new ArrayList<>();

		for (ContactType contactType : ContactType.values()) {
			ContactTypeDto contactTypeDto = new ContactTypeDto();
			contactTypeDto.setCode(contactType.toString());
			contactTypeDto.setName(getName(contactType.toString()));
			list.add(contactTypeDto);
		}

		return Response.ok()
			.entity(new GenericEntity<Collection<ContactTypeDto>>(list){})
			.build();
	}

	private String getName(String code) {
		switch (code) {
			case "CHIEF":
				return "Первый руководитель";

			case "CHIEF_ACCOUNTANT":
				return "Главный бухгалтер";

			case "CHIEF_ENGINEER":
				return "Главный инженер";

			case "OTHER":
				return "Прочее";

			default:
				return code;
		}
	}

	@Context
	private SecurityContext securityContext;
}
