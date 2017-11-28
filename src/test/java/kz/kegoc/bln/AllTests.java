package kz.kegoc.bln;

import kz.kegoc.bln.repository.dict.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        UnitRepositoryTest.class,
        AccountingTypeRepositoryTest.class,
        DataSourceRepositoryTest.class,
        EnergyNodeRepositoryTest.class,
        EnergySourceTypeRepositoryTest.class
})
public class AllTests { }
