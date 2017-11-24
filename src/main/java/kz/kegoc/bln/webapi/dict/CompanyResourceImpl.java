package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.translator.Translator;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.Company;
import kz.kegoc.bln.entity.dict.dto.CompanyDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.CompanyService;

import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictCompany")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class CompanyResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Query query = QueryImpl.builder()
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<CompanyDto> list = service.find(query)
			.stream()
			.map(it -> translator.translate(it, userLang))
			.map(it-> mapper.map(it, CompanyDto.class))
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<CompanyDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Company entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), CompanyDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Company entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), CompanyDto.class))
			.build(); 
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Company entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), CompanyDto.class))
			.build();
	}

	
	@POST
	public Response create(CompanyDto entityDto) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		Company newEntity = service.create(mapper.map(entityDto,Company.class));	
		return Response.ok()
			.entity(mapper.map(translator.translate(newEntity, entityDto.getLang()), CompanyDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, CompanyDto entityDto ) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		Company newEntity = service.update(mapper.map(entityDto,Company.class)); 
		return Response.ok()
			.entity(mapper.map(translator.translate(newEntity, entityDto.getLang()), CompanyDto.class))
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
	private CompanyService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Translator<Company> translator;

	@Inject
	private Lang defLang;
}
