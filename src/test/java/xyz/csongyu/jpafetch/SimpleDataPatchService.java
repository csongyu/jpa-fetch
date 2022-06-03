package xyz.csongyu.jpafetch;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.csongyu.jpafetch.entity.Car;
import xyz.csongyu.jpafetch.entity.Company;
import xyz.csongyu.jpafetch.entity.Employee;
import xyz.csongyu.jpafetch.repository.CompanyRepository;

@Service
public class SimpleDataPatchService {
    @Autowired
    private CompanyRepository repository;

    public void patchData(final String companyCode, final int employeeSize, final int carSize) {
        final Company company = new Company();
        company.setCode(companyCode);
        company.setName("company-" + companyCode);
        final Set<Employee> employees = IntStream.range(0, employeeSize).mapToObj(i -> {
            final Employee employee = new Employee();
            employee.setName("employee-" + i);
            employee.setCode(companyCode + "-employee-" + i);
            employee.setCompany(company);
            final Set<Car> cars = IntStream.range(0, carSize).mapToObj(j -> {
                final Car car = new Car();
                car.setPlate(employee.getCode() + "-car-" + i + "-" + j);
                car.setName("car-" + i + "-" + j);
                car.setEmployee(employee);
                return car;
            }).collect(Collectors.toSet());
            employee.getCars().addAll(cars);
            return employee;
        }).collect(Collectors.toSet());
        company.getEmployees().addAll(employees);
        this.repository.save(company);
    }

    public void clearAll() {
        this.repository.deleteAll();
    }
}
