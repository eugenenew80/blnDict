package kz.kegoc.bln.ejb.mapper;

import org.dozer.DozerBeanMapper;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Arrays;


@Startup
@Singleton
public class BeanMapper {
    private DozerBeanMapper mapper;

    public DozerBeanMapper getMapper() {
        return mapper;
    }

    @PostConstruct
    public void init() {
        mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(
            "mapper/MappingConfig.xml",
            "mapper/dict/def/AccountingTypeDto.xml",
            "mapper/dict/def/OrganizationDto.xml",
            "mapper/dict/def/CurrentTransTypeDto.xml",
            "mapper/dict/def/DataSourceDto.xml",
            "mapper/dict/def/EnergyNodeDto.xml",
            "mapper/dict/def/EnergyDistrictDto.xml",
            "mapper/dict/def/EnergySourceMeteringPointDto.xml",
            "mapper/dict/def/EnergySourceDto.xml",
            "mapper/dict/def/EnergySourceTypeDto.xml",
            "mapper/dict/def/EnergyZoneDto.xml",
            "mapper/dict/def/MeteringPointDto.xml",
            "mapper/dict/def/MeteringPointMeterDto.xml",
            "mapper/dict/def/MeteringPointCurrentTransDto.xml",
            "mapper/dict/def/MeteringPointVoltageTransDto.xml",
            "mapper/dict/def/MeteringPointTypeDto.xml",
            "mapper/dict/def/MeteringTypeDto.xml",
            "mapper/dict/def/MeterDto.xml",
            "mapper/dict/def/MeterTypeDto.xml",
            "mapper/dict/def/RegionDto.xml",
            "mapper/dict/def/SubstationMeteringPointDto.xml",
            "mapper/dict/def/SubstationDto.xml",
            "mapper/dict/def/SubstationTypeDto.xml",
            "mapper/dict/def/UnitDto.xml",
            "mapper/dict/def/VoltageTransTypeDto.xml",
            "mapper/dict/def/CountryDto.xml",
            "mapper/dict/def/BusinessPartnerDto.xml",
            "mapper/dict/def/BankDto.xml",
            "mapper/dict/def/BankAccountDto.xml",
            "mapper/dict/def/PhoneNumberDto.xml",
            "mapper/dict/def/PostAddressDto.xml",
            "mapper/dict/def/VoltageClassDto.xml",
            "mapper/dict/def/ReactorDto.xml",
            "mapper/dict/def/PowerTransformerDto.xml",
            "mapper/dict/def/PowerLineDto.xml",
            "mapper/dict/def/PowerLinePartDto.xml",
            "mapper/dict/def/PowerLineTypeDto.xml",
            "mapper/dict/def/ContactDto.xml",
            "mapper/ecm/def/ContentDto.xml",
            "mapper/ecm/def/BusinessPartnerContentDto.xml",
            "mapper/ecm/def/ContentTypeDto.xml"
        ));
    }
}
