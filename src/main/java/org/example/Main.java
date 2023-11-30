package org.example;

/**
 * 0.1. Посмотреть разные статьи на Хабр.ру про Stream API
 * 0.2. Посмотреть видеоролики на YouTube.com Тагира Валеева про Stream API
 * <p>
 * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
 * 1.1 Найти максимальное
 * 2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
 * 2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
 * <p>
 * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
 * 2.1 Создать список из 10-20 сотрудников
 * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
 * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
 * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
 * 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
 */

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // Создать список из 10 рандомных чисел от 1 до 100
        List<Integer> integerList = new Random().ints(10, 1, 100)
                .boxed().toList();
        System.out.println("\n10 случайных чисел от 1 до 100:\n" + integerList);

        //Найти максимальное
        System.out.println("\nМаксимальное значение:\n" + max(integerList));

        //Все числа, большие, чем 50, умножить на 5, отнять от них 150 и просуммировать
        System.out.println("\nВсе числа, большие, чем 50, умножить на 5, отнять от них 150");
        List<Integer> integers = integerList.stream().map(list -> list * 5 - 150)
                .filter(integer -> integer > 50).toList();
        System.out.println(integers);

        //просуммировать
        int sum = sum(integers);
        System.out.println("\nСумма\n" + sum);

        //Найти количество чисел, квадрат которых меньше, чем 100
        int count = (int) integerList.stream()
                .filter(list -> list * list < 100)
                .count();
        System.out.println("\nНайти количество чисел, квадрат которых меньше, чем 100\n" + count);


        /******************************************** TASK 2*/
        EmployeeRepository employeeRepository = new EmployeeRepository();

        List<String> departmentList = new ArrayList<>();

        //Вывести список всех различных отделов (department) по списку сотрудников
        List<Employee> employeesList = employeeRepository.findEmployees();


        employeesList.stream()
                .map(Employee::getDepartment)
                .distinct()
                .forEachOrdered(departmentList::add);
        System.out.println("\nСписок должностей:" + departmentList);

        //Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
        System.out.println("\nПовышенные зарплаты:");
        employeesList.stream()
                .filter(employee -> employee.getSalary() < 10000)
                .map(employee -> employee.getSalary() * 1.2 )
                .forEach(System.out::println);

        //Из списка сотрудников с помощью стрима создать Map<String, List<Employee>>
        // с отделами и сотрудниками внутри отдела
        Map<String, List<Employee>> department = employeesList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("\nотделы и сотрудниками внутри отдела:\n" + department);

        //Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
        Map<String, Double> salary = employeesList.stream()
                .collect(
                        Collectors.groupingBy(Employee::getDepartment,
                                Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("\nотделы и средняя зарплата внутри отдела:\n" + salary);

    }


    public static int sum(List<Integer> inputs) {
        return inputs.stream().mapToInt(Integer::intValue).sum();
    }

    public static int count(List<Integer> inputs) {
        return (int) inputs.stream().mapToInt(Integer::intValue).count();
    }

    public static Integer max(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).max().getAsInt();
    }

}