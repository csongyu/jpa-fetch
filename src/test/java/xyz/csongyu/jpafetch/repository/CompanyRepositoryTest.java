package xyz.csongyu.jpafetch.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;
import xyz.csongyu.jpafetch.SimpleDataPatchService;
import xyz.csongyu.jpafetch.entity.Company;
import xyz.csongyu.jpafetch.entity.Employee;

@Slf4j
@SpringBootTest
@ActiveProfiles(value = {"test"})
public class CompanyRepositoryTest {
    @Autowired
    private SimpleDataPatchService dataPatchService;

    @Autowired
    private CompanyRepository companyRepository;

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
        log.info("########## @OneToMany JOIN FETCH ##########");
        final Company company = this.companyRepository.findByIdWithUsers("csongyu.xyz");
        log.info("########## @OneToMany JOIN FETCH ##########");
        final Set<Employee> employees =
            Optional.ofNullable(company).map(Company::getEmployees).orElse(Collections.emptySet());
        assertEquals(8, employees.size());
    }
}
