package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergySourceBusinessPartner;
import kz.kegoc.bln.service.dict.EnergySourceBusinessPartnerService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.dto.EnergySourceBusinessPartnerDto;
import kz.kegoc.bln.service.dict.EnergySourceService;

@Stateless
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EnergySourceBusinessPartnerResourceImpl {

	@GET
	public Response getAll(@PathParam("energySourceId") Long energySourceId, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		List<EnergySourceBusinessPartnerDto> list = energySourceService.findById(energySourceId)
			.getBusinessPartners()
			.stream()
			.map( it-> mapper.map(it, EnergySourceBusinessPartnerDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<EnergySourceBusinessPartnerDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		EnergySourceBusinessPartner entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, EnergySourceBusinessPartnerDto.class))
			.build();
	}


	@POST
	public Response create(EnergySourceBusinessPartnerDto entityDto) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		EnergySourceBusinessPartner entity = mapper.map(entityDto, EnergySourceBusinessPartner.class);
		EnergySourceBusinessPartner newEntity = service.create(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, EnergySourceBusinessPartnerDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, EnergySourceBusinessPartnerDto entityDto ) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		EnergySourceBusinessPartner entity = mapper.map(entityDto, EnergySourceBusinessPartner.class);
		EnergySourceBusinessPartner newEntity = service.update(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, EnergySourceBusinessPartnerDto.class))
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
	private EnergySourceService energySourceService;

	@Inject
	private EnergySourceBusinessPartnerService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
