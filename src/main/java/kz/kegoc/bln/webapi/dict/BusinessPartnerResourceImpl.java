package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.entity.dict.dto.BusinessPartnerDto;
import kz.kegoc.bln.repository.common.query.ConditionType;
import kz.kegoc.bln.repository.common.query.MyQueryParam;
import kz.kegoc.bln.repository.common.query.Query;
import kz.kegoc.bln.repository.common.query.QueryImpl;
import kz.kegoc.bln.service.dict.BusinessPartnerService;
import kz.kegoc.bln.translator.Translator;
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

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Stateless
@Path("/dict/dictBusinessPartner")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BusinessPartnerResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Query query = QueryImpl.builder()
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<BusinessPartnerDto> list = businessPartnerService.find(query)
			.stream()
			.map(it -> translator.translate(it, userLang))
			.map( it-> dtoMapper.map(it, BusinessPartnerDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<BusinessPartnerDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		BusinessPartner entity = businessPartnerService.findById(id);
		return Response.ok()
			.entity(dtoMapper.map(translator.translate(entity, userLang), BusinessPartnerDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		BusinessPartner entity = businessPartnerService.findByCode(code);
		return Response.ok()
			.entity(dtoMapper.map(translator.translate(entity, userLang), BusinessPartnerDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		BusinessPartner entity = businessPartnerService.findByName(name);
		return Response.ok()
			.entity(dtoMapper.map(translator.translate(entity, userLang), BusinessPartnerDto.class))
			.build();
	}

	
	@POST
	public Response create(BusinessPartnerDto entityDto) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		BusinessPartner entity = dtoMapper.map(entityDto, BusinessPartner.class);
		BusinessPartner newEntity = businessPartnerService.create(entity);
		if (entity.getBpParent()!=null && entity.getBpParent().getId()==null)
			entity.setBpParent(null);

		return Response.ok()
			.entity(dtoMapper.map(translator.translate(newEntity, entityDto.getLang()), BusinessPartnerDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, BusinessPartnerDto entityDto ) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		BusinessPartner newEntity = businessPartnerService.update(dtoMapper.map(entityDto, BusinessPartner.class));
		return Response.ok()
			.entity(dtoMapper.map(translator.translate(newEntity, entityDto.getLang()), BusinessPartnerDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		businessPartnerService.delete(id);		
		return Response.noContent()
			.build();
	}	
	

	@Inject
	private BusinessPartnerService businessPartnerService;

	@Inject
	private DozerBeanMapper dtoMapper;

	@Context
	private SecurityContext context;

	@Inject
	private Translator<BusinessPartner> translator;

	@Inject
	private Lang defLang;
}
