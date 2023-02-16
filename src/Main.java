public class Main {
    public static void main(String[] args) {
        EmployeeBook eb = new EmployeeBook(10);
        eb.insert("Иванов", 2, 10000);
        eb.insert("Будет удален", 2, 1);
        eb.insert("Петров", 2, 30000);
        eb.insert("Будет удален", 2, 2);
        eb.insert("Сидоров", 2, 1000);
        eb.delete(2);
        eb.delete("Будет удален");
        eb.insert("Иван", 2, 20000);
        eb.insert("Петр", 2, 40000);
        eb.insert("Сидор", 3, 2000);
        eb.insert("Иванoвич", 3, 3000);
        eb.insert("Петрович", 3, 4000);
        eb.insert("Сидорович", 4, 10000);
        System.out.println(eb);

        System.out.println("Сумма зарплат: " + eb.totalSalary());
        System.out.println("Минимальная ЗП у работника: " + eb.employeeMinSalary());
        System.out.println("Максимальная ЗП у работника: " + eb.employeeMaxSalary());
        System.out.println("Средняя ЗП: " + eb.avgSalary());
        System.out.println();
        System.out.println("Все имена:");
        eb.printNames();

        eb.indexSalary(10);
        System.out.println();
        System.out.println("После индексации:");
        System.out.println(eb);

        byte department = 3;
        System.out.println("Работник отдела " + department + " с минимальной ЗП: " + eb.employeeMinSalaryForDepartment(department));
        System.out.println("Работник отдела " + department + " с максимальной ЗП: " + eb.employeeMaxSalaryForDepartment(department));
        System.out.println("Сумма ЗП отдела " + department + ": " + eb.totalSalaryForDepartment(department));
        System.out.println("Средняя ЗП отдела " + department + ": " + eb.avgSalaryForDepartment(department));
        eb.indexSalaryForDepartment(10, department);
        eb.printEmployeesForDepartment(department);
        System.out.println();
        eb.printEmployeesSmallSalary(9000);
        System.out.println();
        eb.printEmployeesBigSalary(9000);

        System.out.println();
        System.out.println("До обновления: " + eb.employeeByName("Сидорович"));
        eb.update("Сидорович", (byte) 5, -1);
        System.out.println("После обновления отдела: " + eb.employeeByName("Сидорович"));
        eb.update("Сидорович", (byte) -1, 100);
        System.out.println("После обновления зп: " + eb.employeeByName("Сидорович"));
        eb.update("Сидорович", (byte) 4, 200);
        System.out.println("После обновления всего: " + eb.employeeByName("Сидорович"));
        System.out.println();
        eb.printEmployees();
    }
}