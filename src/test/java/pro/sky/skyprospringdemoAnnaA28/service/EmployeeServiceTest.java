package pro.sky.skyprospringdemoAnnaA28.service;

import org.junit.jupiter.api.Test;
import pro.sky.skyprospringdemoAnnaA28.domain.Employee;
import pro.sky.skyprospringdemoAnnaA28.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyprospringdemoAnnaA28.exceptions.EmployeeNotFoundException;
import pro.sky.skyprospringdemoAnnaA28.exceptions.EmployeeStorageIsFullException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    EmployeeService employeeService = new EmployeeService();

    @Test
    void testAdd() {
        employeeService.addEmployee("employee1", "Employee1", 10000, 1);
        employeeService.addEmployee("emploYee2", "eMployee2", 60000, 2);

        var actual1 = employeeService.findEmployee("employee1", "Employee1");
        assertThat(actual1).isNotNull();
        assertThat(actual1.getFirstName()).isEqualTo("Employee1");
        assertThat(actual1.getLastName()).isEqualTo("Employee1");
        assertThat(actual1.getDepartmentId()).isEqualTo(1);
        assertThat(actual1.getSalary()).isEqualTo(10000);


        var actual2 = employeeService.findEmployee("EMPLOYEE2", "EMPLOYEE2");
        assertThat(actual2).isNotNull();
        assertThat(actual2.getFirstName()).isEqualTo("EmploYee2");
        assertThat(actual2.getLastName()).isEqualTo("EMployee2");
        assertThat(actual2.getDepartmentId()).isEqualTo(2);
        assertThat(actual2.getSalary()).isEqualTo(60000);
    }

    @Test
    void testAddDublicate() {
        employeeService.addEmployee("employee1", "Employee1", 10000, 1);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee("employee1", "Employee1", 50000, 2));

    }

    @Test
    void fullTest() {   // тут ошибка что имя и фамилия должны состоять из букв, а в строчке 16 или 27 ее нет, почему??((
        employeeService.addEmployee("aaa", "employee", 1000, 1);
        employeeService.addEmployee("bbb", "employee", 1000, 1);
        employeeService.addEmployee("ccc", "employee", 1000, 1);
        employeeService.addEmployee("ddd", "employee", 1000, 1);
        employeeService.addEmployee("eee", "employee", 1000, 1);
        employeeService.addEmployee("fff", "employee", 1000, 1);
        employeeService.addEmployee("jjj", "employee", 1000, 1);
        employeeService.addEmployee("hhh", "employee", 1000, 1);
        employeeService.addEmployee("iii", "employee", 1000, 1);
        employeeService.addEmployee("jjj", "employee", 1000, 1);
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee("kkk", "employee", 1000, 1));

    }

    @Test
    void testWrongName() {
        assertThrows(WrongNameException.class, () -> employeeService.addEmployee("employeea", "1employee", 1000, 2));
        assertThrows(WrongNameException.class, () -> employeeService.addEmployee("employeea", "employee1", 1000, 2));
        assertThrows(WrongNameException.class, () -> employeeService.addEmployee("1employeea", "employee", 1000, 2));
        assertThrows(WrongNameException.class, () -> employeeService.addEmployee("employeea1", "employee", 1000, 2));
    }

    @Test
    void testGetAll() { //тут я тоже не понимаю почему нет containsExactlyInAnyOrder(подчеркнуто красным)
        employeeService.addEmployee("aaa", "employee", 1000, 1);
        employeeService.addEmployee("bbb", "employee", 7000, 4);

        var actual = employeeService.getAll();
        assertThat(actual).containsExactlyInAnyOrder(
                new Employee("Aaa", "employee", 1000, 1),
                new Employee("Bbb", "Employee", 7000, 4));
    }

    @Test
    void testNotFound(){
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("shon", "mmm"));
    }

    @Test
    void testRemove(){
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee("shon", "mmm"));
        employeeService.addEmployee("aaa", "employee", 1000, 1);
        employeeService.addEmployee("bbb", "employee", 7000, 4);
        var actual = employeeService.findEmployee("aaa", "employee");
        assertThat(actual).isNotNull();
        employeeService.removeEmployee("aaa", "employee");
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("aaa", "employee"));
    }

}