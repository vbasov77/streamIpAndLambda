package org.example;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public List<Employee> findEmployees() {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Sergey", 23, 8000.00, "advertisement"));
        employees.add(new Employee("Tanya", 31, 85000.00, "IT"));
        employees.add(new Employee("Valeriy", 40, 120000.00, "IT"));
        employees.add(new Employee("Anna", 29, 95000.00, "IT"));
        employees.add(new Employee("Julia", 25, 68000.00, "advertisement"));
        employees.add(new Employee("Victor", 43, 100000.00, "management"));
        employees.add(new Employee("Lara", 18, 9000.00, "advertisement"));

        return employees;
    }
}
