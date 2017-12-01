package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.entity.dict.dto.PowerLineDto;
import kz.kegoc.bln.repository.common.query.ConditionType;
import kz.kegoc.bln.repository.common.query.MyQueryParam;
import kz.kegoc.bln.repository.common.query.Query;
import kz.kegoc.bln.repository.common.query.QueryImpl;
import kz.kegoc.bln.service.dict.PowerLineService;
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
@Path("/dict/dictPowerLine")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class PowerLineResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		Query query = QueryImpl.builder()
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<PowerLineDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, PowerLineDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<PowerLineDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		PowerLine entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, PowerLineDto.class))
			.build();		
	}


	@POST
	public Response create(PowerLineDto entityDto) {
		Lang userLang = (entityDto.getLang()==null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		PowerLine entity = mapper.map(entityDto, PowerLine.class);
		PowerLine newEntity = service.create(entity);
		
		return Response.ok()
			.entity(mapper.map(newEntity, PowerLineDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, PowerLineDto entityDto ) {
		Lang userLang = (entityDto.getLang()==null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		PowerLine entity = mapper.map(entityDto, PowerLine.class);
		PowerLine newEntity = service.update(entity);
		
		return Response.ok()
			.entity(mapper.map(newEntity, PowerLineDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}


	@Path("/{powerLineId : \\d+}/dictPowerLinePart")
	public PowerLinePartResourceImpl getPowerLinePartResource(@PathParam("powerLineId") Long id) {
		return powerLinePartResource;
	}


	@Inject
	private PowerLinePartResourceImpl powerLinePartResource;

	@Inject
	private PowerLineService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;
}
