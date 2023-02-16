public class Employee {
    private static int counter=1;
    private final int id;
    private String name;
    private byte department;
    private int salary;
    public Employee(String name, int department, int salary) {
        this.id = counter++;
        this.name = name;
        this.department = (byte)department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public void setDepartment(byte department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Работник {" +
                "id=" + id +
                ", name='" + name + "'" +
                ", department=" + department +
                ", salary=" + salary +
                "}";
    }
}
