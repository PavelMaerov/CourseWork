public class EmployeeBook {
    private final Employee[] employees;
    public EmployeeBook(int size) {
        employees = new Employee[size];
    }

    //1.Получить список всех сотрудников со всеми имеющимися по ним данными
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Employee emp : employees) {
            sb.append(emp == null ? "пусто\n" : emp + "\n");
        }
        return sb.toString();
    }

    //2. Посчитать сумму затрат на зарплаты в месяц.
    public int totalSalary() {
        int sum = 0;
        for (Employee emp : employees) if (emp != null) sum += emp.getSalary();
        return sum;
    }

    //3. Найти сотрудника с минимальной зарплатой.
    public Employee employeeMinSalary() {  //попытался решить задачу одной строкой
        Employee empMin = new Employee("", 0, 1000000000);
        for (Employee emp : employees) if (emp != null && emp.getSalary() < empMin.getSalary()) empMin = emp;
        return empMin.getSalary() == 1000000000 ? null : empMin;
    }

    //4. Найти сотрудника с максимальной зарплатой.
    public Employee employeeMaxSalary() {
        Employee empMax = new Employee("", 0, 0);
        for (Employee emp : employees) if (emp != null && emp.getSalary() > empMax.getSalary()) empMax = emp;
        return empMax.getSalary() == 0 ? null : empMax;
    }

    //5. Подсчитать среднее значение зарплат (можно использовать для этого метод из пункта b).
    public double avgSalary() {
        return employees.length == 0 ? 0 : totalSalary() / (double) employees.length;
    }

    //6. Получить Ф. И. О. всех сотрудников (вывести в консоль).
    public void printNames() {
        for (Employee emp : employees) if (emp != null) System.out.println(emp.getName());
    }

    //1. Проиндексировать зарплату (вызвать изменение зарплат у всех сотрудников на величину аргумента в %).
    public void indexSalary(int percent) {
        for (Employee emp : employees)
            if (emp != null)
                emp.setSalary((int) (emp.getSalary() * (1 + percent / 100d)));
    }

    //2. Получить в качестве параметра номер отдела (1–5) и найти (всего 6 методов):
    //    1. Сотрудника с минимальной зарплатой.
    public Employee employeeMinSalaryForDepartment(byte department) { //здесь вариант традиционный с двумя переменными
        Employee empMin = null;
        int min = 1000000000;
        for (Employee emp : employees)
            if (emp != null && emp.getDepartment() == department && emp.getSalary() < min) {
                min = emp.getSalary();
                empMin = emp;
            }
        return empMin;
    }

    //    2. Сотрудника с максимальной зарплатой.
    public Employee employeeMaxSalaryForDepartment(byte department) { //здесь вариант традиционный с двумя переменными
        Employee empMax = null;
        int max = 0;
        for (Employee emp : employees)
            if (emp != null && emp.getDepartment() == department && emp.getSalary() > max) {
                max = emp.getSalary();
                empMax = emp;
            }
        return empMax;
    }

    //    3. Сумму затрат на зарплату по отделу.
    private int peopleForDepartment; //для возврата второго значения из totalSalaryForDepartment

    public int totalSalaryForDepartment(byte department) { //неявно возвращает peopleForDepartment. Надеюсь, никто второй с моим объектом не работает
        int sum = 0;
        peopleForDepartment = 0;
        for (Employee emp : employees)
            if (emp != null && emp.getDepartment() == department) {
                peopleForDepartment++;
                sum += emp.getSalary();
            }
        return sum;
    }

    //    4. Среднюю зарплату по отделу (учесть, что количество людей в отделе отличается от employees.length).
    public double avgSalaryForDepartment(byte department) {
        int total = totalSalaryForDepartment(department);
        return peopleForDepartment == 0 ? 0 : total / (double) peopleForDepartment;
    }

    //    5. Проиндексировать зарплату всех сотрудников отдела на процент, который приходит в качестве параметра.
    public void indexSalaryForDepartment(int percent, byte department) {
        for (Employee emp : employees)
            if (emp != null && emp.getDepartment() == department)
                emp.setSalary((int) (emp.getSalary() * (1 + percent / 100d)));
    }

    //    6. Напечатать всех сотрудников отдела (все данные, кроме отдела).
    public void printEmployeesForDepartment(byte department) {
        System.out.println("Работники отдела " + department + ":");
        for (Employee emp : employees)
            if (emp != null && emp.getDepartment() == department)
                System.out.println("Работник {" +
                        "id=" + emp.getId() +
                        ", name='" + emp.getName() + "'" +
                        ", salary=" + emp.getSalary() +
                        "}");
    }

    //3. Получить в качестве параметра число и найти:
    //    1. Всех сотрудников с зарплатой меньше числа (вывести id, Ф. И. О. и зарплатой в консоль).
    public void printEmployeesSmallSalary(int limit) {
        System.out.println("Работники c ЗП меньше " + limit + ":");
        for (Employee emp : employees) if (emp != null && emp.getSalary() < limit) System.out.println(emp);
    }

    //    2. Всех сотрудников с зарплатой больше (или равно) числа (вывести id, Ф. И. О. и зарплатой в консоль).
    public void printEmployeesBigSalary(int limit) {
        System.out.println("Работники c ЗП больше " + limit + ":");
        for (Employee emp : employees) if (emp != null && emp.getSalary() > limit) System.out.println(emp);
    }

    //4.1. Добавить нового сотрудника (создаем объект, заполняем поля, кладем в массив).
    public void insert(String name, int department, int salary) {
        int i = 0;
        while (i < employees.length && employees[i] != null) i++;
        if (i == employees.length) System.out.println("Нет свободных мест в справочнике");
        else employees[i] = new Employee(name, department, salary);
    }

    //4.2. Удалить сотрудника (находим сотрудника по Ф. И. О. и/или id, обнуляем его ячейку в массиве).
    private int indexByName(String name) {
        //найти работника по имени - нам пригодится и при удалении, и при обновлении, и для печати отдельного работника
        int i = 0;
        while (i < employees.length && !(employees[i] == null ? "" : employees[i].getName()).equals(name)) {
            i++;
        }
        return i;
    }

    public void delete(String name) {
        int i = indexByName(name);
        if (i == employees.length) System.out.println("Не найден сотрудник с именем '" + name + "' для удаления");
        else employees[i] = null;
    }

    //4.2 Удаление по Id
    public void delete(int id) {  //не стал делать indexById, т.к. обновления по Id не заказывали
        int i = 0;
        while (i < employees.length && (employees[i] == null ? 0 : employees[i].getId()) != id) {
            i++;
        }
        if (i == employees.length) System.out.println("Не найден сотрудник с Id " + id + " для удаления");
        else employees[i] = null;
    }

    //5. Изменить сотрудника (получить сотрудника по Ф. И. О., модернизировать его запись):
    //    1. Изменить зарплату.
    //    2. Изменить отдел.
    //    Придумать архитектуру. Сделать или два метода, или один, но продумать его.
    public void update(String name, byte department, int salary) {  //salary<0 - не устанавливать, department<0 - не устанавливать
        int i = indexByName(name);
        if (i == employees.length) {
            System.out.println("Не найден сотрудник с именем '" + name + "' для обновления");
            return;
        }
        if (department >= 0) employees[i].setDepartment(department);
        if (salary >= 0) employees[i].setSalary(salary);
    }

    //для печати результатов обновления
    public Employee employeeByName(String name) {
        int i = indexByName(name);
        if (i == employees.length) {
            System.out.println("Не найден сотрудник с именем '" + name + "'");
            return null;
        } else return employees[i];
    }

    //6. Получить Ф. И. О. всех сотрудников по отделам (напечатать список отделов и их сотрудников).
    public void printEmployees() { //напечатаем имена только тех отделов, где есть работники
        System.out.println("Имена работников по отделам");
        for (byte department = Byte.MIN_VALUE; department < Byte.MAX_VALUE; department++) {
            boolean firstInDepartment=true;
            for (Employee emp:employees)
                if (emp != null && emp.getDepartment() == department) {
                    if (firstInDepartment) System.out.println("Отдел "+department);
                    System.out.println(emp.getName());
                    firstInDepartment=false;
                }
        }
    }
}
