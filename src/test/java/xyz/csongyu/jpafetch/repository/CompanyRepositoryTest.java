package xyz.csongyu.jpafetch.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Optional;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;
import xyz.csongyu.jpafetch.SimpleDataPatchService;
import xyz.csongyu.jpafetch.entity.Company;

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
    public void testFindById() {
        log.info("########## @OneToMany LAZY ##########");
        final Optional<Company> company = this.companyRepository.findById("csongyu.xyz");
        log.info("########## @OneToMany LAZY ##########");
        assertTrue(company.isPresent());
    }

    @Test
    public void testFindByIdWithUsersGetException() {
        assertThrows(LazyInitializationException.class, () -> {
            final Optional<Company> company = this.companyRepository.findById("csongyu.xyz");
            company.map(Company::getEmployees).orElse(Collections.emptySet()).size();
        });
    }
}
