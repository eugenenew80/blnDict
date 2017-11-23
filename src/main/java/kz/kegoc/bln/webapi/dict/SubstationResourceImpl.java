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
import kz.kegoc.bln.entity.dict.Substation;
import kz.kegoc.bln.entity.dict.dto.SubstationDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.SubstationService;
import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictSubstation")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class SubstationResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Query query = QueryImpl.builder()
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<SubstationDto> list = service.find(query)
			.stream()
			.map(it -> translator.translate(it, userLang))
			.map( it-> mapper.map(it, SubstationDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<SubstationDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Substation entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), SubstationDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Substation entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), SubstationDto.class))
			.build(); 
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Substation entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), SubstationDto.class))
			.build();
	}

	
	@POST
	public Response create(SubstationDto entityDto) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		Substation newEntity = service.create(mapper.map(entityDto, Substation.class));
		return Response.ok()
			.entity(mapper.map(translator.translate(newEntity, entityDto.getLang()), SubstationDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, SubstationDto entityDto ) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		Substation newEntity = service.update(mapper.map(entityDto, Substation.class)); 
		return Response.ok()
			.entity(mapper.map(translator.translate(newEntity, entityDto.getLang()), SubstationDto.class))
			.build();
	}

	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Path("/{substationId : \\d+}/dictSubstationCompany")
	public SubstationCompanyResourceImpl getModules(@PathParam("substationId") Long id) {
		return substationCompanyResource;
	}


	@Path("/{substationId : \\d+}/dictSubstationMeteringPoint")
	public SubstationMeteringPointResourceImpl getMeteringPoints(@PathParam("substationId") Long id) {
		return substationMeteringPointResource;
	}


	@Inject
	private SubstationService service;

	@Inject
	private SubstationCompanyResourceImpl substationCompanyResource;

	@Inject
	private SubstationMeteringPointResourceImpl substationMeteringPointResource;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Translator<Substation> translator;

	@Inject
	private Lang defLang;
}
