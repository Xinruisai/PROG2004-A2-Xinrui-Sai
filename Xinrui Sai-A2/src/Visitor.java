public class Visitor {
    // 游客属性：姓名、年龄、门票号、访问日期
    private String name;
    private int age;
    private String ticketId;
    private String visitDate;

    // 无参构造器
    public Visitor() {}

    // 带参构造器
    public Visitor(String name, int age, String ticketId, String visitDate) {
        this.name = name;
        this.age = age;
        this.ticketId = ticketId;
        this.visitDate = visitDate;
    }

    // Getter和Setter（所有属性）
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    // 打印游客信息（测试用）
    @Override
    public String toString() {
        return "Visitor{name='" + name + "', age=" + age + ", ticketId='" + ticketId + "', visitDate='" + visitDate + "'}";
    }
}