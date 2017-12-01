package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLineType;
import kz.kegoc.bln.entity.dict.dto.PowerLineTypeDto;
import kz.kegoc.bln.repository.common.query.ConditionType;
import kz.kegoc.bln.repository.common.query.MyQueryParam;
import kz.kegoc.bln.repository.common.query.Query;
import kz.kegoc.bln.repository.common.query.QueryImpl;
import kz.kegoc.bln.service.dict.PowerLineTypeService;
import org.dozer.DozerBeanMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Stateless
@Path("/dict/dictPowerLineType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class PowerLineTypeResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		Query query = QueryImpl.builder()
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<PowerLineTypeDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, PowerLineTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<PowerLineTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		PowerLineType entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, PowerLineTypeDto.class))
			.build();		
	}


	@POST
	public Response create(PowerLineTypeDto entityDto) {
		Lang userLang = (entityDto.getLang()==null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		PowerLineType entity = mapper.map(entityDto, PowerLineType.class);
		PowerLineType newEntity = service.create(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, PowerLineTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, PowerLineTypeDto entityDto ) {
		Lang userLang = (entityDto.getLang()==null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		PowerLineType entity = mapper.map(entityDto, PowerLineType.class);
		PowerLineType newEntity = service.update(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, PowerLineTypeDto.class))
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
	private PowerLineTypeService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;
}
