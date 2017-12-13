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
import kz.kegoc.bln.entity.dict.DataSource;
import kz.kegoc.bln.entity.dict.dto.DataSourceDto;
import kz.kegoc.bln.service.dict.DataSourceService;


@Stateless
@Path("/dict/dictDataSource")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class DataSourceResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @HeaderParam("lang") Lang lang) {
		List<DataSourceDto> list = service.findAll(buildSessionContext(lang))
			.stream()
			.map( it-> mapper.map(it, DataSourceDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<DataSourceDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		DataSource dataSource = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(dataSource, DataSourceDto.class))
			.build();		
	}
	

	@POST
	public Response create(DataSourceDto entityDto, @HeaderParam("lang") Lang lang) {
		DataSource entity = mapper.map(entityDto, DataSource.class);
		DataSource newDataSource = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newDataSource, DataSourceDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, DataSourceDto entityDto, @HeaderParam("lang") Lang lang ) {
		DataSource entity = mapper.map(entityDto, DataSource.class);
		DataSource newDataSource = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.map(newDataSource, DataSourceDto.class))
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
	private DataSourceService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
