package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.common.OrgType;
import kz.kegoc.bln.entity.dict.dto.OrgTypeDto;
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
@Path("/dict/dictOrgType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class OrgTypeResourceImpl {

	@GET 
	public Response getAll(@HeaderParam("lang") Lang lang) {
		List<OrgTypeDto> list = new ArrayList<>();

		for (OrgType orgType : OrgType.values()) {
			OrgTypeDto orgTypeDto = new OrgTypeDto();
			orgTypeDto.setCode(orgType.toString());
			orgTypeDto.setName(getName(orgType.toString()));
			list.add(orgTypeDto);
		}

		return Response.ok()
			.entity(new GenericEntity<Collection<OrgTypeDto>>(list){})
			.build();
	}

	private String getName(String code) {
		switch (code) {
			case "HEAD":
				return "Головная компания";

			case "BRANCH":
				return "Филиал";

			case "AFFILIATED":
				return "Дочерняя компания";

			case "DEPARTMENT":
				return "Департамент";

			default:
				return code;
		}
	}

	@Context
	private SecurityContext securityContext;
}
