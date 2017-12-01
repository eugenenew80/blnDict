package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointCharacteristic;
import kz.kegoc.bln.entity.dict.dto.MeteringPointCharacteristicDto;
import kz.kegoc.bln.service.dict.MeteringPointCharacteristicService;
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
public class MeteringPointCharacteristicResourceImpl {

	@GET
	public Response getAll(@PathParam("meteringPointId") Long meteringPointId, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		List<MeteringPointCharacteristicDto> list = meteringPointService.findById(meteringPointId)
			.getCharacteristics()
			.stream()
			.map( it-> mapper.map(it, MeteringPointCharacteristicDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<MeteringPointCharacteristicDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		MeteringPointCharacteristic entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, MeteringPointCharacteristicDto.class))
			.build();
	}


	@POST
	public Response create(MeteringPointCharacteristicDto entityDto) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		MeteringPointCharacteristic entity = mapper.map(entityDto, MeteringPointCharacteristic.class);
		MeteringPointCharacteristic newEntity = service.create(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointCharacteristicDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, MeteringPointCharacteristicDto entityDto ) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang(): defLang);
		service.setLang(userLang);

		MeteringPointCharacteristic entity = mapper.map(entityDto, MeteringPointCharacteristic.class);
		MeteringPointCharacteristic newEntity = service.update(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointCharacteristicDto.class))
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
	private MeteringPointCharacteristicService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;
}
