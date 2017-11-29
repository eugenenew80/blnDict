package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import kz.kegoc.bln.entity.common.Lang;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.Meter;
import kz.kegoc.bln.entity.dict.dto.MeterDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.MeterService;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Stateless
@Path("/dict/dictMeter")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeterResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		Query query = QueryImpl.builder()
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<MeterDto> list = service.find(query)
			.stream()
			.map(it-> mapper.map(it, MeterDto.class))
			.collect(Collectors.toList());

		return Response.ok()
			.entity(new GenericEntity<Collection<MeterDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		Meter entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, MeterDto.class))
			.build();		
	}

	
	@POST
	public Response create(MeterDto entityDto) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		Meter entity = mapper.map(entityDto, Meter.class);
		Meter newEntity = service.create(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, MeterDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MeterDto entityDto ) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		Meter entity = mapper.map(entityDto, Meter.class);
		Meter newEntity = service.update(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, MeterDto.class))
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
	private MeterService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;
}
