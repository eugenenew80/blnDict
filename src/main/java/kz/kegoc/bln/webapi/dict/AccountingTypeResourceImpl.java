package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.AccountingType;
import kz.kegoc.bln.entity.dict.dto.AccountingTypeDto;
import kz.kegoc.bln.service.dict.AccountingTypeService;


@Stateless
@Path("/dict/dictAccountingType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class AccountingTypeResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @HeaderParam("lang") Lang lang) {
		List<AccountingTypeDto> list = service.findAll(buildSessionContext(lang))
			.stream()
			.map( it-> mapper.map(it, AccountingTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<AccountingTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		AccountingType entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, AccountingTypeDto.class))
			.build();		
	}


	@POST
	public Response create(AccountingTypeDto entityDto, @HeaderParam("lang") Lang lang) {
		AccountingType entity = mapper.map(entityDto, AccountingType.class);
		AccountingType newEntity = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, AccountingTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, AccountingTypeDto entityDto, @HeaderParam("lang") Lang lang) {
		AccountingType entity = mapper.map(entityDto, AccountingType.class);
		AccountingType newEntity = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, AccountingTypeDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		service.delete(id, buildSessionContext(lang));
		return Response.noContent()
			.build();
	}


	private SessionContext buildSessionContext(Lang lang) {
		SessionContext context = new SessionContext();
		context.setLang(lang!=null ? lang : defLang);
		context.setUser(((CustomPrincipal)securityContext.getUserPrincipal()).getUser());
		return context;
	}

	@Inject
	private AccountingTypeService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
