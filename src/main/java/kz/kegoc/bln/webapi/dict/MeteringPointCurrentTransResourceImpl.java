package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointCurrentTrans;
import kz.kegoc.bln.entity.dict.dto.MeteringPointCurrentTransDto;
import kz.kegoc.bln.service.dict.MeteringPointCurrentTransService;
import kz.kegoc.bln.service.dict.MeteringPointService;
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
public class MeteringPointCurrentTransResourceImpl {

	@GET
	public Response getAll(@PathParam("meteringPointId") Long meteringPointId, @QueryParam("lang") Lang lang) {
		List<MeteringPointCurrentTransDto> list = meteringPointService.findById(meteringPointId, buildSessionContext(lang))
			.getCurrentTrans()
			.stream()
			.map( it-> mapper.getMapper().map(it, MeteringPointCurrentTransDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<MeteringPointCurrentTransDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		MeteringPointCurrentTrans entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, MeteringPointCurrentTransDto.class))
			.build();
	}


	@POST
	public Response create(MeteringPointCurrentTransDto entityDto) {
		MeteringPointCurrentTrans entity = mapper.getMapper().map(entityDto, MeteringPointCurrentTrans.class);
		MeteringPointCurrentTrans newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, MeteringPointCurrentTransDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, MeteringPointCurrentTransDto entityDto ) {
		MeteringPointCurrentTrans entity = mapper.getMapper().map(entityDto, MeteringPointCurrentTrans.class);
		MeteringPointCurrentTrans newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, MeteringPointCurrentTransDto.class))
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
	private MeteringPointService meteringPointService;

	@Inject
	private MeteringPointCurrentTransService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
