package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.BankAccount;
import kz.kegoc.bln.entity.dict.dto.BankAccountDto;
import kz.kegoc.bln.service.dict.BusinessPartnerService;
import kz.kegoc.bln.service.dict.BankAccountService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
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
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BankAccountResourceImpl {

	@GET
	public Response getAll(@PathParam("businessPartnerId") Long businessPartnerId, @HeaderParam("lang") Lang lang) {
		List<BankAccountDto> list = businessPartnerService.findById(businessPartnerId, buildSessionContext(lang))
			.getBankAccounts()
			.stream()
			.map( it-> mapper.map(it, BankAccountDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<BankAccountDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		BankAccount entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, BankAccountDto.class))
			.build();
	}


	@POST
	public Response create(BankAccountDto entityDto, @HeaderParam("lang") Lang lang) {
		BankAccount entity = mapper.map(entityDto, BankAccount.class);
		BankAccount newEntity = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, BankAccountDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, BankAccountDto entityDto, @HeaderParam("lang") Lang lang) {
		BankAccount entity = mapper.map(entityDto, BankAccount.class);
		BankAccount newEntity = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newEntity, BankAccountDto.class))
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
	private BusinessPartnerService businessPartnerService;

	@Inject
	private BankAccountService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
