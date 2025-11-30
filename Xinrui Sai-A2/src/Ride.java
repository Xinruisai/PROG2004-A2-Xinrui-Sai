import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ride implements RideInterface {
    private String rideName;
    private Employee operator;
    private Queue<Visitor> waitingQueue = new LinkedList<>();
    private LinkedList<Visitor> rideHistory = new LinkedList<>();
    private int maxRider;
    private int numOfCycles = 0;

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

    // Part3 队列方法
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

    // Part4A 历史方法
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

    // Part4B 排序方法
    public void sortRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("❌ " + rideName + "历史记录为空，无需排序");
            return;
        }
        Collections.sort(rideHistory, new VisitorComparator());
        System.out.println("✅ " + rideName + "历史已按【年龄升序+票号升序】排序完成");
    }

    // Part5 骑行周期方法
    @Override
    public void runOneCycle() {
        System.out.println("\n=== " + rideName + " 运行单次骑行周期（Xinrui Sai） ===");
        if (this.operator == null) {
            System.out.println("❌ 错误：无操作员，无法运行骑行！");
            return;
        }
        if (waitingQueue.isEmpty()) {
            System.out.println("❌ 错误：等待队列为空，无法运行骑行！");
            return;
        }
        int takeNum = Math.min(maxRider, waitingQueue.size());
        System.out.println("✅ 本次可搭载游客数：" + takeNum + "人（单次最大：" + maxRider + "人）");
        for (int i = 0; i < takeNum; i++) {
            Visitor v = waitingQueue.poll();
            if (v != null) {
                rideHistory.add(v);
                System.out.println("→ 游客" + v.getName() + "已完成骑行，加入历史记录");
            }
        }
        numOfCycles++;
        System.out.println("✅ " + rideName + "已运行" + numOfCycles + "次周期，剩余队列游客数：" + waitingQueue.size());
    }

    // Part6 CSV导出方法（核心：路径改为C:\Users\33123\Desktop）
    public void exportRideHistory() {
        // 已适配你的实际路径：C:\Users\33123\Desktop
        String filePath = "C:\\Users\\33123\\Desktop\\ride_history.csv";
        System.out.println("\n=== 导出" + rideName + "历史到CSV（Xinrui Sai） ===");

        if (rideHistory.isEmpty()) {
            System.out.println("❌ 历史记录为空，无需导出");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("name,age,ticketId,visitDate");
            bw.newLine();
            for (Visitor v : rideHistory) {
                String line = v.getName() + "," + v.getAge() + "," + v.getTicketId() + "," + v.getVisitDate();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("✅ 成功导出到：" + filePath);
            System.out.println("✅ 可在桌面找到 ride_history.csv 文件");
        } catch (IOException e) {
            System.out.println("❌ 导出失败：" + e.getMessage());
        }
    }

    // Getter和Setter
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public Queue<Visitor> getWaitingQueue() { return waitingQueue; }
    public LinkedList<Visitor> getRideHistory() { return rideHistory; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = maxRider; }
    public int getNumOfCycles() { return numOfCycles; }
}