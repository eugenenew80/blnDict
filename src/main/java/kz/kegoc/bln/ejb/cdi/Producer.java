package kz.kegoc.bln.ejb.cdi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import kz.kegoc.bln.entity.common.Lang;
import org.dozer.DozerBeanMapper;
import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import kz.kegoc.bln.entity.adm.User;

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
    public Lang defLang() {
        return Lang.RU;
    }

    @Produces
    public CriteriaBuilder getCrieriaBuilder() { return em.getCriteriaBuilder(); };

    @PersistenceContext(unitName = "bln")
    private EntityManager em;
}
