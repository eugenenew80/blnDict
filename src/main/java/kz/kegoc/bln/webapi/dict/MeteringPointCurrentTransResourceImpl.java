package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointCurrentTrans;
import kz.kegoc.bln.entity.dict.dto.MeteringPointCurrentTransDto;
import kz.kegoc.bln.service.dict.MeteringPointCurrentTransService;
import kz.kegoc.bln.service.dict.MeteringPointService;
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
public class MeteringPointCurrentTransResourceImpl {

	@GET
	public Response getAll(@PathParam("meteringPointId") Long meteringPointId, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		List<MeteringPointCurrentTransDto> list = meteringPointService.findById(meteringPointId)
			.getCurrentTrans()
			.stream()
			.map( it-> mapper.map(it, MeteringPointCurrentTransDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<MeteringPointCurrentTransDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		MeteringPointCurrentTrans entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, MeteringPointCurrentTransDto.class))
			.build();
	}


	@POST
	public Response create(MeteringPointCurrentTransDto entityDto) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		MeteringPointCurrentTrans entity = mapper.map(entityDto, MeteringPointCurrentTrans.class);
		MeteringPointCurrentTrans newEntity = service.create(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointCurrentTransDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, MeteringPointCurrentTransDto entityDto ) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		MeteringPointCurrentTrans entity = mapper.map(entityDto, MeteringPointCurrentTrans.class);
		MeteringPointCurrentTrans newEntity = service.update(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointCurrentTransDto.class))
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
	private MeteringPointService meteringPointService;

	@Inject
	private MeteringPointCurrentTransService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;
}
