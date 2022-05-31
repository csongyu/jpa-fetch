package xyz.csongyu.jpafetch.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import xyz.csongyu.jpafetch.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Set<Employee> findByCodeIn(Set<String> codes);

    @Query("from Employee e join fetch e.address where e.code in :code")
    Set<Employee> queryByCodeIn(@Param("code") Set<String> codes);
}
