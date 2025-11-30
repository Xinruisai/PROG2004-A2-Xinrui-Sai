import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

// 关键：加implements RideInterface，实现接口
public class Ride implements RideInterface {
    // 原有属性
    private String rideName;
    private Employee operator;
    // 新增：Part3队列、Part4历史（提前初始化，避免空指针）
    private Queue<Visitor> waitingQueue = new LinkedList<>();
    private LinkedList<Visitor> rideHistory = new LinkedList<>();

    // 原有构造器不变
    public Ride() {}
    public Ride(String rideName, Employee operator) {
        this.rideName = rideName;
        this.operator = operator;
    }

    // 实现接口的8个方法（先写基础逻辑，能跑就行）
    @Override
    public void addVisitorToQueue(Visitor visitor) {
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

    @Override
    public void addVisitorToHistory(Visitor visitor) {
        rideHistory.add(visitor);
        System.out.println("✅ 游客" + visitor.getName() + "已加入" + rideName + "历史");
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        // 按门票号判断（唯一标识）
        boolean exists = rideHistory.stream().anyMatch(v -> v.getTicketId().equals(visitor.getTicketId()));
        System.out.println("🔍 游客" + visitor.getName() + "是否在历史：" + exists);
        return exists;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("📊 " + rideName + "历史游客数：" + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n📜 " + rideName + "骑行历史（共" + rideHistory.size() + "人）：");
        if (rideHistory.isEmpty()) {
            System.out.println("历史为空");
            return;
        }
        Iterator<Visitor> it = rideHistory.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println(i++ + ". " + it.next());
        }
    }

    @Override
    public void runOneCycle() {
        // 暂时留空，后续补
        System.out.println("⚠️  待实现runOneCycle方法");
    }

    // 原有Getter和Setter不变（新增队列和历史的Getter，可选）
    public Queue<Visitor> getWaitingQueue() {
        return waitingQueue;
    }

    public LinkedList<Visitor> getRideHistory() {
        return rideHistory;
    }
}