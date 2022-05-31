package xyz.csongyu.jpafetch.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;
import xyz.csongyu.jpafetch.entity.Address;
import xyz.csongyu.jpafetch.entity.Employee;
import xyz.csongyu.jpafetch.repository.EmployeeRepository;

@Slf4j
@SpringBootTest
@ActiveProfiles(value = {"test"})
public class EmployeeServiceImplTest {
    protected static final int EMPLOYEE_SIZE = 1_000;

    protected static final List<String> CITY = Arrays.asList("XA", "GZ", "HK");

    @Autowired
    private EmployeeRepository repository;

    @BeforeEach
    public void before() {
        final Random random = new Random();

        final List<Employee> employees = new ArrayList<>(EMPLOYEE_SIZE);
        this.getEmployeeCodes(EMPLOYEE_SIZE).forEach(code -> {
            final Employee employee = new Employee();
            employees.add(employee);
            employee.setCode(code);

            final Address address = new Address();
            address.setCity(CITY.get(random.nextInt(CITY.size())));
            address.setEmployee(employee);

            employee.setAddress(address);
        });

        this.repository.saveAll(employees);
    }

    @AfterEach
    public void after() {
        log.info("########## after ##########");
        this.repository.deleteAll();
    }

    protected List<String> getEmployeeCodes(final int size) {
        return IntStream.range(0, size).mapToObj(i -> "ZZ" + String.format("%04d", i)).collect(Collectors.toList());
    }
}
