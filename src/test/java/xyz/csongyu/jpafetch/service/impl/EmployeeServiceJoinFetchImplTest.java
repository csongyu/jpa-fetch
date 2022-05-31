package xyz.csongyu.jpafetch.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import xyz.csongyu.jpafetch.service.EmployeeService;

class EmployeeServiceJoinFetchImplTest extends EmployeeServiceImplTest {
    @Autowired
    @Qualifier("employeeServiceJoinFetchImpl")
    private EmployeeService employeeService;

    @Test
    public void testGetMappingFromEmployeeCodeToAddressCity() {
        final Map<String, String> result = this.employeeService
            .getMappingFromEmployeeCodeToAddressCity(new HashSet<>(this.getEmployeeCodes(EMPLOYEE_SIZE)));
        assertEquals(EMPLOYEE_SIZE, result.size());
    }
}