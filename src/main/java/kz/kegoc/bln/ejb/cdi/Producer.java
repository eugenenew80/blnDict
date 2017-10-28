package kz.kegoc.bln.ejb.cdi;

import org.dozer.DozerBeanMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Arrays;

@ApplicationScoped
public class Producer {
	DozerBeanMapper mapper = null;
	
    @Produces
    public DozerBeanMapper dozerBeanMapper() {
    	if (mapper!=null)
    		return mapper;
    	
        mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(
            "mapping/MappingConfig.xml",
            "mapping/dict/AccountingTypeDtoDefaultMapping.xml",
            "mapping/dict/CompanyDtoDefaultMapping.xml",
            "mapping/dict/CurrentTransDtoDefaultMapping.xml",
            "mapping/dict/CurrentTransTypeDtoDefaultMapping.xml",
            "mapping/dict/DataSourceDtoDefaultMapping.xml",
            "mapping/dict/EnergyNodeDtoDefaultMapping.xml",
            "mapping/dict/EnergySourceCompanyDtoDefaultMapping.xml",
            "mapping/dict/EnergySourceMeteringPointDtoDefaultMapping.xml",
            "mapping/dict/EnergySourceDtoDefaultMapping.xml",
            "mapping/dict/EnergySourceTypeDtoDefaultMapping.xml",
            "mapping/dict/EnergyZoneDtoDefaultMapping.xml",
            "mapping/dict/MeteringPointDtoDefaultMapping.xml",
            "mapping/dict/MeteringPointTypeDtoDefaultMapping.xml",
            "mapping/dict/MeteringTypeDtoDefaultMapping.xml",
            "mapping/dict/MeterDtoDefaultMapping.xml",
            "mapping/dict/MeterTypeDtoDefaultMapping.xml",
            "mapping/dict/RegionDtoDefaultMapping.xml",
            "mapping/dict/SubstationCompanyDtoDefaultMapping.xml",
            "mapping/dict/SubstationMeteringPointDtoDefaultMapping.xml",
            "mapping/dict/SubstationDtoDefaultMapping.xml",
            "mapping/dict/SubstationTypeDtoDefaultMapping.xml",
            "mapping/dict/UnitDtoDefaultMapping.xml",
            "mapping/dict/VoltageTransDtoDefaultMapping.xml",
            "mapping/dict/VoltageTransTypeDtoDefaultMapping.xml",
            "mapping/dict/list/MeterDtoForListMapping.xml"
        ));
        return mapper;
    }
}
