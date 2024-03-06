package pro.sky.skyprospringdemoAnnaA28.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringdemoAnnaA28.domain.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findMaxSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee findMinSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Collection<Employee> findByDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> groupByDepartment() {
    return employeeService.getAll().stream()
            .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }


}
