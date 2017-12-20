package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.SubstationMeteringPoint;
import kz.kegoc.bln.entity.dict.dto.SubstationMeteringPointDto;
import kz.kegoc.bln.service.dict.SubstationMeteringPointService;
import kz.kegoc.bln.service.dict.SubstationService;
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
public class SubstationMeteringPointResourceImpl {

	@GET
	public Response getAll(@PathParam("substationId") Long substationId, @QueryParam("lang") Lang lang) {
		List<SubstationMeteringPointDto> list = substationService.findById(substationId, buildSessionContext(lang))
			.getMeteringPoints()
			.stream()
			.map( it-> mapper.getMapper().map(it, SubstationMeteringPointDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<SubstationMeteringPointDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		SubstationMeteringPoint entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, SubstationMeteringPointDto.class))
			.build();
	}


	@POST
	public Response create(SubstationMeteringPointDto entityDto) {
		SubstationMeteringPoint entity = mapper.getMapper().map(entityDto, SubstationMeteringPoint.class);
		SubstationMeteringPoint newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, SubstationMeteringPointDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, SubstationMeteringPointDto entityDto ) {
		SubstationMeteringPoint entity = mapper.getMapper().map(entityDto, SubstationMeteringPoint.class);
		SubstationMeteringPoint newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, SubstationMeteringPointDto.class))
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
	private SubstationService substationService;

	@Inject
	private SubstationMeteringPointService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
