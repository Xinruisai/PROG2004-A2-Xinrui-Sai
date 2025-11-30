public class Employee extends Person {
    // 员工专属属性：员工号、职位
    private String empId;
    private String position;

    // 无参构造器
    public Employee() {}

    // 带参构造器（父类+子类属性）
    public Employee(String name, int age, String idCard, String empId, String position) {
        super(name, age, idCard);
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