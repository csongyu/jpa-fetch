package xyz.csongyu.jpafetch.dao.impl;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import xyz.csongyu.jpafetch.dao.CompanyDao;
import xyz.csongyu.jpafetch.entity.Company;
import xyz.csongyu.jpafetch.entity.Employee;
import xyz.csongyu.jpafetch.repository.CompanyRepository;

@Slf4j
@Repository
public class CompanyDaoImpl implements CompanyDao {
    private final CompanyRepository repository;

    public CompanyDaoImpl(final CompanyRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Company findByIdWithUsers(final String id) {
        final Optional<Company> company = this.repository.findById(id);
        company.ifPresent(entity -> {
            final Set<Employee> employees = entity.getEmployees();
            log.info("company {} has {} employees", id, employees.size());
        });
        return company.orElse(null);
    }
}
