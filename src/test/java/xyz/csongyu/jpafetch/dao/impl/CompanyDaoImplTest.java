package xyz.csongyu.jpafetch.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;
import xyz.csongyu.jpafetch.SimpleDataPatchService;
import xyz.csongyu.jpafetch.dao.CompanyDao;
import xyz.csongyu.jpafetch.entity.Company;

@Slf4j
@SpringBootTest
@ActiveProfiles(value = {"test"})
public class CompanyDaoImplTest {
    @Autowired
    private SimpleDataPatchService dataPatchService;

    @Autowired
    private CompanyDao companyDao;

    @BeforeEach
    public void before() {
        this.dataPatchService.patchData("csongyu.xyz", 8, 2);
    }

    @AfterEach
    public void after() {
        this.dataPatchService.clearAll();
    }

    @Test
    public void testFindByIdWithUsers() {
        log.info("########## @OneToMany LAZY retrieve collection ##########");
        final Company company = this.companyDao.findByIdWithUsers("csongyu.xyz");
        log.info("########## @OneToMany LAZY retrieve collection ##########");
        assertNotNull(company);
        assertEquals(8, company.getEmployees().size());
    }
}
