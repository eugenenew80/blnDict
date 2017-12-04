package kz.kegoc.bln.ejb.cdi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.Lang;
import org.dozer.DozerBeanMapper;
import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Arrays;

@ApplicationScoped
public class Producer {
    private RedissonClient redissonClient = null;
    private RMapCache<String, User> sessions  = null;
	DozerBeanMapper mapper = null;

    @Produces
    public RedissonClient createRedissonClient() {
        if (redissonClient !=null)
            return redissonClient;

        ObjectMapper mapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setCodec(new JsonJacksonCodec(mapper));

        redissonClient = Redisson.create(config);
        return redissonClient;
    }


    @Produces
    public RMapCache<String, User> createSessions() {
        if (sessions!=null)
            return sessions;

        createRedissonClient();
        sessions = redissonClient.getMapCache("sessions");
        return sessions;
    }


    @Produces
    public DozerBeanMapper dozerBeanMapper() {
    	if (mapper!=null)
    		return mapper;
    	
        mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(
                "mapper/MappingConfig.xml",
                "mapper/dict/def/AccountingTypeDto.xml",
                "mapper/dict/def/OrganizationDto.xml",
                "mapper/dict/def/CurrentTransTypeDto.xml",
                "mapper/dict/def/DataSourceDto.xml",
                "mapper/dict/def/EnergyNodeDto.xml",
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
                "mapper/dict/list/MeterDto.xml"

        ));
        return mapper;
    }

    @Produces
    public Lang defLang() {
        return Lang.RU;
    }
}
