package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Bank;
import kz.kegoc.bln.entity.dict.dto.BankDto;
import kz.kegoc.bln.repository.common.query.ConditionType;
import kz.kegoc.bln.repository.common.query.MyQueryParam;
import kz.kegoc.bln.repository.common.query.Query;
import kz.kegoc.bln.repository.common.query.QueryImpl;
import kz.kegoc.bln.service.dict.BankService;
import kz.kegoc.bln.translator.Translator;
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
@Path("/dict/dictBank")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BankResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Query query = QueryImpl.builder()
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<BankDto> list = service.find(query)
			.stream()
			.map(it -> translator.translate(it, userLang))
			.map( it-> mapper.map(it, BankDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<BankDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Bank entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), BankDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Bank entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), BankDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Bank entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), BankDto.class))
			.build();
	}

	
	@POST
	public Response create(BankDto entityDto) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		Bank newEntity = service.create(mapper.map(entityDto, Bank.class));
		return Response.ok()
			.entity(mapper.map(translator.translate(newEntity, entityDto.getLang()), BankDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, BankDto entityDto ) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		Bank newEntity = service.update(mapper.map(entityDto, Bank.class));
		return Response.ok()
			.entity(mapper.map(translator.translate(newEntity, entityDto.getLang()), BankDto.class))
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
	private BankService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Translator<Bank> translator;

	@Inject
	private Lang defLang;
}
