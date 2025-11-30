import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

public class Ride implements RideInterface {
    // 基础属性
    private String rideName;
    private Employee operator;
    // Part3队列属性
    private Queue<Visitor> waitingQueue = new LinkedList<>();
    // 后续属性占位
    private LinkedList<Visitor> rideHistory = new LinkedList<>();
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

    // ========== Part3 队列核心方法（完整实现） ==========
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

    // 后续方法空实现（保证接口不报错）
    @Override
    public void addVisitorToHistory(Visitor visitor) {}
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) { return false; }
    @Override
    public int numberOfVisitors() { return 0; }
    @Override
    public void printRideHistory() {}
    @Override
    public void runOneCycle() {}

    // Getter和Setter
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public Queue<Visitor> getWaitingQueue() { return waitingQueue; }
}