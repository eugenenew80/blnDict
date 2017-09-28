package kz.kegoc.bln.webapi.config;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import kz.kegoc.bln.webapi.dict.DataSourceResourceImpl;
import kz.kegoc.bln.webapi.dict.EnergyNodeResourceImpl;
import kz.kegoc.bln.webapi.dict.EnergySourceResourceImpl;
import kz.kegoc.bln.webapi.dict.EnergySourceTypeResourceImpl;
import kz.kegoc.bln.webapi.dict.EnergyZoneResourceImpl;
import kz.kegoc.bln.webapi.dict.MeterResourceImpl;
import kz.kegoc.bln.webapi.dict.MeterTypeResourceImpl;
import kz.kegoc.bln.webapi.dict.MeteringPointResourceImpl;
import kz.kegoc.bln.webapi.dict.MeteringPointTypeResourceImpl;
import kz.kegoc.bln.webapi.dict.MeteringTypeResourceImpl;
import kz.kegoc.bln.webapi.dict.RegionResourceImpl;
import kz.kegoc.bln.webapi.dict.SubstationResourceImpl;
import kz.kegoc.bln.webapi.dict.SubstationTypeResourceImpl;
import kz.kegoc.bln.service.auth.impl.AuthServiceImpl;
import kz.kegoc.bln.webapi.adm.FuncResourceImpl;
import kz.kegoc.bln.webapi.adm.RoleResourceImpl;
import kz.kegoc.bln.webapi.adm.UserResourceImpl;
import kz.kegoc.bln.webapi.dict.AccountingTypeResourceImpl;
import kz.kegoc.bln.webapi.dict.CompanyResourceImpl;
import kz.kegoc.bln.webapi.dict.CurrentTransResourceImpl;
import kz.kegoc.bln.webapi.dict.CurrentTransTypeResourceImpl;
import kz.kegoc.bln.webapi.dict.UnitResourceImpl;
import kz.kegoc.bln.webapi.dict.VoltageTransResourceImpl;
import kz.kegoc.bln.webapi.dict.VoltageTransTypeResourceImpl;
import kz.kegoc.bln.webapi.exception.mapper.EntityNotFoundExceptionMapperImpl;
import kz.kegoc.bln.webapi.exception.mapper.DefaultExceptionMapperImpl;
import kz.kegoc.bln.webapi.exception.mapper.DuplicateEntityExceptionMapperImpl;
import kz.kegoc.bln.webapi.exception.mapper.EjbExceptionMapperImpl;
import kz.kegoc.bln.webapi.exception.mapper.InvalidArgumentExceptionMapperImpl;
import kz.kegoc.bln.webapi.exception.mapper.RepositryNotFoundExceptionMapperImpl;
import kz.kegoc.bln.webapi.exception.mapper.ValidationExceptionMapperImpl;
import kz.kegoc.bln.webapi.exception.mapper.WebApplicationExceptionMapperImpl;
import kz.kegoc.bln.webapi.filters.BasicAuthentificationFilter;
import kz.kegoc.bln.webapi.meta.MetaAdmResourceImpl;
import kz.kegoc.bln.webapi.meta.MetaDictResourceImpl;
import kz.kegoc.bln.webapi.meta.MetaModuleResourceImpl;


@ApplicationPath("/webapi")
public class JaxRsConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resources = new HashSet<Class<?>>();

		resources.add(AuthServiceImpl.class);
		
		resources.add(UnitResourceImpl.class);
		resources.add(DataSourceResourceImpl.class);
		resources.add(MeterTypeResourceImpl.class);
		resources.add(CurrentTransTypeResourceImpl.class);
		resources.add(VoltageTransTypeResourceImpl.class);
		resources.add(EnergyZoneResourceImpl.class);
		resources.add(RegionResourceImpl.class);
		resources.add(EnergyNodeResourceImpl.class);
		resources.add(CompanyResourceImpl.class);
		resources.add(MeterResourceImpl.class);
		resources.add(CurrentTransResourceImpl.class);
		resources.add(VoltageTransResourceImpl.class);
		resources.add(EnergySourceTypeResourceImpl.class);
		resources.add(SubstationResourceImpl.class);
		resources.add(EnergySourceResourceImpl.class);
		resources.add(MeteringTypeResourceImpl.class);
		resources.add(SubstationTypeResourceImpl.class);
		resources.add(AccountingTypeResourceImpl.class);
		resources.add(MeteringPointTypeResourceImpl.class);	
		resources.add(MeteringPointResourceImpl.class);
				
		resources.add(FuncResourceImpl.class);
		resources.add(RoleResourceImpl.class);
		resources.add(UserResourceImpl.class);
		
		resources.add(MetaModuleResourceImpl.class);
		resources.add(MetaDictResourceImpl.class);
		resources.add(MetaAdmResourceImpl.class);
		
		
		resources.add(BasicAuthentificationFilter.class);
		
		resources.add(RepositryNotFoundExceptionMapperImpl.class);
		resources.add(EntityNotFoundExceptionMapperImpl.class);
		resources.add(DuplicateEntityExceptionMapperImpl.class);
		resources.add(ValidationExceptionMapperImpl.class);
		resources.add(InvalidArgumentExceptionMapperImpl.class);
		resources.add(EjbExceptionMapperImpl.class);
		resources.add(WebApplicationExceptionMapperImpl.class);
        resources.add(DefaultExceptionMapperImpl.class);
        
		return resources;
	}	
}
