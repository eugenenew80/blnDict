package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.DataSource;
import kz.kegoc.bln.entity.dict.dto.DataSourceDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.DataSourceService;

import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictDataSource")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class DataSourceResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<DataSourceDto> list = dataSourceService.find(query)
			.stream()
			.map( it-> mapper.map(it, DataSourceDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<DataSourceDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		DataSource dataSource = dataSourceService.findById(id);
		return Response.ok()
			.entity(mapper.map(dataSource, DataSourceDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		DataSource dataSource = dataSourceService.findByCode(code);
		return Response.ok()
			.entity(mapper.map(dataSource, DataSourceDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		DataSource dataSource = dataSourceService.findByName(name);
		return Response.ok()
			.entity(mapper.map(dataSource, DataSourceDto.class))
			.build();
	}

	
	@POST
	public Response create(DataSourceDto entityDto) {
		DataSource newDataSource = dataSourceService.create(mapper.map(entityDto, DataSource.class));
		return Response.ok()
			.entity(mapper.map(newDataSource, DataSourceDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, DataSourceDto entityDto ) {
		DataSource newDataSource = dataSourceService.update(mapper.map(entityDto, DataSource.class));
		return Response.ok()
			.entity(mapper.map(newDataSource, DataSourceDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		dataSourceService.delete(id);		
		return Response.noContent()
			.build();
	}	
	

	@Inject
	private DataSourceService dataSourceService;

	@Inject
	private DozerBeanMapper mapper;

	@Context
	private SecurityContext context;
}
