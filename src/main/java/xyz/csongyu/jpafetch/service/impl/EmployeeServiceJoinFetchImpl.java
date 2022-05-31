package xyz.csongyu.jpafetch.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import xyz.csongyu.jpafetch.entity.Address;
import xyz.csongyu.jpafetch.entity.Employee;
import xyz.csongyu.jpafetch.repository.EmployeeRepository;
import xyz.csongyu.jpafetch.service.EmployeeService;

@Slf4j
@Service
public class EmployeeServiceJoinFetchImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceJoinFetchImpl(final EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, String> getMappingFromEmployeeCodeToAddressCity(final Set<String> employeeCodes) {
        if (Objects.isNull(employeeCodes) || employeeCodes.size() <= 0) {
            throw new IllegalArgumentException("employee codes should not be empty");
        }

        log.info("########## @OneToOne JOIN FETCH ##########");
        final Set<Employee> employees = this.repository.queryByCodeIn(employeeCodes);
        log.info("########## @OneToOne JOIN FETCH ##########");

        return Optional.ofNullable(employees).orElse(Collections.emptySet()).stream()
            .collect(Collectors.toMap(Employee::getCode, employee -> {
                final Address address = employee.getAddress();
                if (Objects.isNull(address) || Objects.isNull(address.getCity())) {
                    return "";
                }
                return address.getCity();
            }));
    }
}
