public class Employee extends Person {
    private String empId;
    private String position;

    public Employee() {}

    public Employee(String name, int age, String idCard, String empId, String position) {
        super(name, age, idCard);
        this.empId = empId;
        this.position = position;
    }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}