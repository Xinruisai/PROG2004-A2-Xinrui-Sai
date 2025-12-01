/**
 * PROG2004 A2 Video Script (Xinrui Sai)
 * 1. 面向对象设计（ULO2）：
 *    - Person作为抽象类，Employee/Visitor继承复用核心属性（姓名、年龄）；
 *    - Ride实现RideInterface接口，强制实现队列、历史、骑行周期等所有业务方法，保证接口一致性。
 * 2. 高级集合（ULO3）：
 *    - 等待队列使用Queue（LinkedList）实现FIFO特性，符合排队逻辑；
 *    - 历史记录使用LinkedList，结合VisitorComparator实现按年龄升序+票号升序排序。
 * 3. IO操作（ULO4）：
 *    - 实现CSV导出/导入功能，路径适配Windows系统（C:\Users\33123\Desktop），处理IO异常和数据格式错误；
 *    - 导出时自动生成表头，导入时跳过表头，保证数据完整性。
 * 4. 核心业务逻辑：
 *    - runOneCycle方法实现队列转历史，限制单次搭载人数，统计运行周期数；
 *    - 导入前清空历史，避免数据重复，提升数据准确性。
 */
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

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

    // ========== Part4B 排序方法 ==========
    public void sortRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("❌ " + rideName + "历史记录为空，无需排序");
            return;
        }
        Collections.sort(rideHistory, new VisitorComparator());
        System.out.println("✅ " + rideName + "历史已按【年龄升序+票号升序】排序完成");
    }

    // ========== Part5 骑行周期方法 ==========
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

    // ========== Part6 CSV导出方法（路径适配33123） ==========
    public void exportRideHistory() {
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

    // ========== Part7 CSV导入方法（核心新增，路径适配33123） ==========
    public void importRideHistory() {
        String filePath = "C:\\Users\\33123\\Desktop\\ride_history.csv";
        System.out.println("\n=== 从CSV导入" + rideName + "历史（Xinrui Sai） ===");

        // 清空原有历史（避免重复导入）
        rideHistory.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; // 跳过CSV表头
            int importCount = 0;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                // 拆分CSV字段（按逗号分隔）
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.out.println("⚠️  跳过无效行：" + line);
                    continue;
                }
                // 创建游客对象并添加到历史
                Visitor v = new Visitor(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]);
                rideHistory.add(v);
                importCount++;
                System.out.println("→ 导入成功：" + v);
            }
            System.out.println("✅ 导入完成！共导入" + importCount + "名游客");
        } catch (IOException e) {
            System.out.println("❌ 导入失败（文件不存在/路径错误）：" + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("❌ 导入失败（年龄格式错误）：" + e.getMessage());
        }
    }

    // Getter和Setter（完整）
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