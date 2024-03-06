package pro.sky.skyprospringdemoAnnaA28.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.skyprospringdemoAnnaA28.domain.Employee;
import pro.sky.skyprospringdemoAnnaA28.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyprospringdemoAnnaA28.exceptions.EmployeeNotFoundException;
import pro.sky.skyprospringdemoAnnaA28.exceptions.EmployeeStorageIsFullException;


import java.util.*;

@Service
public class EmployeeService {
    private final int MAX_COUNT = 10;
    public Map<String, Employee> employees = new HashMap<>(MAX_COUNT);


    public void addEmployee(String firstName, String lastName, int salary, int departmentId) throws EmployeeAlreadyAddedException {
        if (StringUtils.isAlpha(firstName) || StringUtils.isAlpha(lastName)){
            throw new WrongNameException("Имя и фамилия должны состоять только из букв");
        }

        if (employees.size() >= MAX_COUNT) {
            throw new EmployeeStorageIsFullException();
        }


        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), salary, departmentId);
        var key = makeKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(key, employee);

    }


    public void removeEmployee(String firstName, String lastName) {
        var key = makeKey(firstName, lastName);
        var removed = employees.remove(key);
        if (removed == null) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(key);
    }

    public Employee findEmployee(String firstName, String lastName) {
        var key = makeKey(firstName, lastName);
        var employee = employees.get(key);
        if (employee != null) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());

    }

    private static String makeKey(String firstName, String lastName) {
        return (firstName + "_" + lastName).toLowerCase();
    }

}