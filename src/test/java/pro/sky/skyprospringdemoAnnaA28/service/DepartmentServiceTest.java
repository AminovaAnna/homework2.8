package pro.sky.skyprospringdemoAnnaA28.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skyprospringdemoAnnaA28.domain.Employee;
import pro.sky.skyprospringdemoAnnaA28.exceptions.EmployeeNotFoundException;

import java.awt.*;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() { //тут не хочет писаться of
        var employees = List.of(
                new Employee("aaa", "employee", 1000, 1),
                new Employee("bbb", "employee", 2000, 1),
                new Employee("ccc", "employee", 15000, 2),
                new Employee("eee", "employee", 7000, 3),
                new Employee("jjj", "employee", 10000, 2),
                new Employee("hhh", "employee", 11000, 1));

        when(employeeService.getAll()).thenReturn(employees);
    }

    @Test
    void testDepartmentMaxSalary() {
        assertThat(departmentService.findMaxSalary(1)).isEqualTo(new Employee("hhh", "employee", 11000, 1));
        assertThat(departmentService.findMaxSalary(2)).isEqualTo(new Employee("ccc", "employee", 15000, 2));
        assertThat(departmentService.findMaxSalary(3)).isEqualTo(new Employee("eee", "employee", 7000, 3));
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findMaxSalary(100));
    }

    @Test
    void testDepartmentMinSalary() {
        assertThat(departmentService.findMinSalary(1)).isEqualTo(new Employee("aaa", "employee", 1000, 1));
        assertThat(departmentService.findMinSalary(2)).isEqualTo(new Employee("jjj", "employee", 10000, 2));
        assertThat(departmentService.findMinSalary(3)).isEqualTo(new Employee("eee", "employee", 7000, 3));
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findMinSalary(100));
    }

    @Test
    void testFindByDepartment() { //тут тоже красным containsExactlyInAnyOrder и isEmpty
        var actual = departmentService.findByDepartment(3);
        assertThat(actual).containsExactlyInAnyOrder(
                new Employee("eee", "employee", 7000, 3));

        var actual2 = departmentService.findByDepartment(100);
        assertThat(actual2).isEmpty();

    }

    @Test
    void groupByDepartment() { // тут снова красным of  при написании List.of
        var actual = departmentService.groupByDepartment();

        var expected = Map.of(1, List.of(new Employee("aaa", "employee", 1000, 1),
                             new Employee("bbb", "employee", 2000, 1),
                             new Employee("hhh", "employee", 11000, 1)),
                2, List.of(          new Employee("ccc", "employee", 15000, 2),new Employee("jjj", "employee", 10000, 2)),
                        3, List.of(new Employee("eee", "employee", 7000, 3)));
        assertThat(actual).isEqualTo(expected);
    }
}