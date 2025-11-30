public class Employee extends Person {
    // 员工专属属性：员工号、职位（如"骑行操作员"）
    private String empId;
    private String position;

    // 无参构造器
    public Employee() {}

    // 带参构造器（先传父类Person的属性，再传自己的）
    public Employee(String name, int age, String idCard, String empId, String position) {
        super(name, age, idCard); // 调用父类构造器
        this.empId = empId;
        this.position = position;
    }

    // Getter和Setter
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}