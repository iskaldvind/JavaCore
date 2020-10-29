package io.iskaldvind;

public class Lesson5 {

    private static final int EMPLOYEESSIZE = 5;
    private static final int AGELIMIT = 40;

    public static void main(String[] args) {

        Employee[] employees = new Employee[EMPLOYEESSIZE];
        employees[0] = new Employee("Barak Obama", "Truck driver", "barakobama@pentagon.gov", "+111111111", 1000, 59);
        employees[1] = new Employee("Spiderman", "Industrial climber", "xxxsmanxxx@marvel.com", "+1888888888", 100, 20);
        employees[2] = new Employee("Ivan Susanin", "Guide", "susanin@mail.ru", "+79267277777", 200, 66);
        employees[3] = new Employee("Linus Torvalds", "Software Engineer", "tuxrider@example.com", "+3581234567", 10000, 51);
        employees[4] = new Employee("Terminator T-800", "Terminator", "astalavista@cyberdyne.com", "+1101110010", 0, 404);

        for (Employee employee: employees) {
            if (employee.getAge() > AGELIMIT) employee.toConsole();
        }
    }
}
