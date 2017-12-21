package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Bank;
import kz.kegoc.bln.entity.dict.dto.BankDto;
import kz.kegoc.bln.service.dict.BankService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.apache.commons.lang3.StringUtils;
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

@Stateless
@Path("/dict/dictBank")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BankResourceImpl {

	@GET 
	public Response getAll(
		@QueryParam("searchValue") String searchValue,
		@QueryParam("name") String name,
		@HeaderParam("lang") Lang lang
	) {
		if (StringUtils.isNotEmpty(searchValue))
			name = searchValue;

		List<BankDto> list = service.find(null, null, name, buildSessionContext(lang))
			.stream()
			.map( it-> mapper.getMapper().map(it, BankDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<BankDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		Bank entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, BankDto.class))
			.build();		
	}
	

	@POST
	public Response create(BankDto entityDto, @HeaderParam("lang") Lang lang) {
		Bank entity = mapper.getMapper().map(entityDto, Bank.class);
		Bank newEntity = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, BankDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, BankDto entityDto, @HeaderParam("lang") Lang lang) {
		Bank entity = mapper.getMapper().map(entityDto, Bank.class);
		Bank newEntity = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, BankDto.class))
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
	private BankService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
