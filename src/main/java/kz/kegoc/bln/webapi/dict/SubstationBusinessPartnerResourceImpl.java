package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.SubstationBusinessPartner;
import kz.kegoc.bln.service.dict.SubstationBusinessPartnerService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.dto.SubstationBusinessPartnerDto;
import kz.kegoc.bln.service.dict.SubstationService;

@Stateless
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class SubstationBusinessPartnerResourceImpl {

	@GET
	public Response getAll(@PathParam("substationId") Long substationId, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		List<SubstationBusinessPartnerDto> list = substationService.findById(substationId)
			.getBusinessPartners()
			.stream()
			.map( it-> mapper.map(it, SubstationBusinessPartnerDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<SubstationBusinessPartnerDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		SubstationBusinessPartner entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, SubstationBusinessPartnerDto.class))
			.build();
	}


	@POST
	public Response create(SubstationBusinessPartnerDto entityDto) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang(): defLang);
		service.setLang(userLang);

		SubstationBusinessPartner entity = mapper.map(entityDto, SubstationBusinessPartner.class);
		SubstationBusinessPartner newEntity = service.create(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, SubstationBusinessPartnerDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, SubstationBusinessPartnerDto entityDto ) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		SubstationBusinessPartner entity = mapper.map(entityDto, SubstationBusinessPartner.class);
		SubstationBusinessPartner newEntity = service.update(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, SubstationBusinessPartnerDto.class))
			.build();
	}


	@DELETE
	@Path("{id : \\d+}")
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);
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
	private SubstationService substationService;

	@Inject
	private SubstationBusinessPartnerService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
