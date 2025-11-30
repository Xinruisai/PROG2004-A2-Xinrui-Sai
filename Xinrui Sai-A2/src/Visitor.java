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

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }
    public String getVisitDate() { return visitDate; }
    public void setVisitDate(String visitDate) { this.visitDate = visitDate; }

    @Override
    public String toString() {
        return "Visitor{name='" + name + "', age=" + age + ", ticketId='" + ticketId + "', visitDate='" + visitDate + "'}";
    }
}