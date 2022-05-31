package xyz.csongyu.jpafetch.service;

import java.util.Map;
import java.util.Set;

public interface EmployeeService {
    Map<String, String> getMappingFromEmployeeCodeToAddressCity(Set<String> employeeCodes);
}
