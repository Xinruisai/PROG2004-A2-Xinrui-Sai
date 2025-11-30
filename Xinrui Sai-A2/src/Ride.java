import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;

public class Ride implements RideInterface {
    // 基础属性
    private String rideName;
    private Employee operator;
    // Part3队列属性
    private Queue<Visitor> waitingQueue = new LinkedList<>();
    // Part4A历史属性
    private LinkedList<Visitor> rideHistory = new LinkedList<>();
    // 后续属性占位
    private int maxRider;
    private int numOfCycles = 0;

    // 构造器
    public Ride() {}
    public Ride(String rideName, Employee operator) {
        this.rideName = rideName;
        this.operator = operator;
    }
    public Ride(String rideName, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.operator = operator;
        this.maxRider = maxRider;
    }

    // ========== Part3 队列方法 ==========
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ 游客信息不能为空！");
            return;
        }
        waitingQueue.offer(visitor);
        System.out.println("✅ 游客" + visitor.getName() + "已加入" + rideName + "队列");
    }

    @Override
    public void removeVisitorFromQueue() {
        Visitor removed = waitingQueue.poll();
        if (removed != null) {
            System.out.println("✅ 已移除" + rideName + "队列游客：" + removed.getName());
        } else {
            System.out.println("❌ " + rideName + "队列为空，无法移除");
        }
    }

    @Override
    public void printQueue() {
        System.out.println("\n📋 " + rideName + "当前队列（共" + waitingQueue.size() + "人）：");
        if (waitingQueue.isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        int i = 1;
        for (Visitor v : waitingQueue) {
            System.out.println(i++ + ". " + v);
        }
    }

    // ========== Part4A 历史方法 ==========
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ 游客信息不能为空！");
            return;
        }
        rideHistory.add(visitor);
        System.out.println("✅ 游客" + visitor.getName() + "已加入" + rideName + "历史");
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ 游客信息不能为空！");
            return false;
        }
        boolean exists = rideHistory.stream().anyMatch(v -> v.getTicketId().equals(visitor.getTicketId()));
        System.out.println("🔍 游客" + visitor.getName() + "（票号：" + visitor.getTicketId() + "）是否在历史：" + exists);
        return exists;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("📊 " + rideName + "历史游客总数：" + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n📜 " + rideName + "骑行历史（共" + rideHistory.size() + "人）：");
        if (rideHistory.isEmpty()) {
            System.out.println("历史记录为空");
            return;
        }
        int i = 1;
        Iterator<Visitor> it = rideHistory.iterator();
        while (it.hasNext()) {
            System.out.println(i++ + ". " + it.next());
        }
    }

    // ========== Part4B 排序方法（完整实现，无需添加） ==========
    public void sortRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("❌ " + rideName + "历史记录为空，无需排序");
            return;
        }
        Collections.sort(rideHistory, new VisitorComparator());
        System.out.println("✅ " + rideName + "历史已按【年龄升序+票号升序】排序完成");
    }

    // Part5方法空实现（占位）
    @Override
    public void runOneCycle() {}

    // Getter和Setter（完整）
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public Queue<Visitor> getWaitingQueue() { return waitingQueue; }
    public LinkedList<Visitor> getRideHistory() { return rideHistory; }
}