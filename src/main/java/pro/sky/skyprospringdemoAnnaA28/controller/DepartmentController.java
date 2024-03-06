package pro.sky.skyprospringdemoAnnaA28.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skyprospringdemoAnnaA28.domain.Employee;
import pro.sky.skyprospringdemoAnnaA28.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")

public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping(path = "/max-salary")
    public Employee max(@RequestParam int departmentId) {
        return service.findMaxSalary(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee min(@RequestParam int departmentId) {
        return service.findMinSalary(departmentId);
    }

    @GetMapping(value = "/all", params ={"departmentId"})
    public Collection<Employee> findAllByDepartment(@RequestParam int departmentId) {
        return service.findByDepartment(departmentId);
    }

    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> groupByDepartment() {
        return service.groupByDepartment();
    }



}
