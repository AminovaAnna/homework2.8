package pro.sky.skyprospringdemoAnnaA28.exceptions;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeNotFoundException extends RuntimeException{
}
