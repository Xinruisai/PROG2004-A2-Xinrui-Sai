public class Visitor {
    private String name;
    private int age;
    private String ticketId;
    private String visitDate;

    public Visitor() {}

    public Visitor(String name, int age, String ticketId, String visitDate) {
        this.name = name;
        this.age = age;
        this.ticketId = ticketId;
        this.visitDate = visitDate;
    }

    // Getter和Setter（略，按之前步骤用IDE生成）

    // 打印游客信息（Xinrui Sai 专属模板，直接用）
    @Override
    public String toString() {
        return "Visitor{name='" + name + "', age=" + age + ", ticketId='" + ticketId + "', visitDate='" + visitDate + "'}";
    }
}