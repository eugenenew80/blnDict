package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.common.Lang;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.CurrentTrans;
import kz.kegoc.bln.entity.dict.dto.CurrentTransDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.CurrentTransService;

import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictCurrentTrans")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class CurrentTransResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		Query query = QueryImpl.builder()
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<CurrentTransDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, CurrentTransDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<CurrentTransDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		CurrentTrans entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, CurrentTransDto.class))
			.build();		
	}
	

	@POST
	public Response create(CurrentTransDto entityDto) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		CurrentTrans entity = mapper.map(entityDto, CurrentTrans.class);
		CurrentTrans newEntity = service.create(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, CurrentTransDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id,CurrentTransDto entityDto ) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		CurrentTrans entity = mapper.map(entityDto, CurrentTrans.class);
		CurrentTrans newEntity = service.update(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, CurrentTransDto.class))
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
	private CurrentTransService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;
}
