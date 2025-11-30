import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;

// Xinrui Sai 专属：实现RideInterface接口
public class Ride implements RideInterface {
    private String rideName;
    private Employee operator;
    private Queue<Visitor> waitingQueue = new LinkedList<>();
    private LinkedList<Visitor> rideHistory = new LinkedList<>();
    // Part5新增属性：单次最大人数、运行次数
    private int maxRider;
    private int numOfCycles = 0;

    // 构造器（适配你的项目）
    public Ride() {}
    public Ride(String rideName, Employee operator) {
        this.rideName = rideName;
        this.operator = operator;
    }
    // Part5带maxRider的构造器
    public Ride(String rideName, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.operator = operator;
        this.maxRider = maxRider;
    }

    // 所有接口方法（已实现，直接复制，无需改）
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

    // Part5核心：运行单次骑行周期
    @Override
    public void runOneCycle() {
        System.out.println("\n=== " + rideName + " 运行单次周期 ===");
        if (operator == null) {
            System.out.println("❌ 无操作员，无法运行！");
            return;
        }
        if (waitingQueue.isEmpty()) {
            System.out.println("❌ 队列无游客，无法运行！");
            return;
        }
        int takeNum = Math.min(maxRider, waitingQueue.size());
        System.out.println("✅ 本次可搭载" + takeNum + "人（单次最大：" + maxRider + "人）");
        for (int i = 0; i < takeNum; i++) {
            Visitor v = waitingQueue.poll();
            if (v != null) {
                rideHistory.add(v);
                System.out.println("→ 游客" + v.getName() + "已完成骑行，加入历史");
            }
        }
        numOfCycles++;
        System.out.println("✅ " + rideName + "已运行" + numOfCycles + "次周期");
    }

    // Part4B排序方法
    public void sortRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("❌ 历史为空，无需排序");
            return;
        }
        Collections.sort(rideHistory, new VisitorComparator());
        System.out.println("✅ " + rideName + "历史已按【年龄升序+票号升序】排序");
    }

    // Part6导出CSV（路径用你的桌面路径）
    public void exportRideHistory() {
        String filePath = "C:/Users/Xinrui Sai/Desktop/ride_history.csv"; // Windows用这个
        // Mac用：String filePath = "/Users/Xinrui Sai/Desktop/ride_history.csv";
        System.out.println("\n=== 导出" + rideName + "历史到CSV ===");
        if (rideHistory.isEmpty()) {
            System.out.println("❌ 历史为空，无需导出");
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
        } catch (IOException e) {
            System.out.println("❌ 导出失败：" + e.getMessage());
        }
    }

    // Part7导入CSV（路径和导出一致）
    public void importRideHistory() {
        String filePath = "C:/Users/Xinrui Sai/Desktop/ride_history.csv"; // Windows用这个
        // Mac用：String filePath = "/Users/Xinrui Sai/Desktop/ride_history.csv";
        System.out.println("\n=== 从CSV导入" + rideName + "历史 ===");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.out.println("⚠️  跳过无效行：" + line);
                    continue;
                }
                Visitor v = new Visitor(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]);
                rideHistory.add(v);
                System.out.println("→ 导入游客：" + v);
            }
            System.out.println("✅ 导入完成，共导入" + rideHistory.size() + "名游客");
        } catch (IOException e) {
            System.out.println("❌ 导入失败（文件不存在）：" + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("❌ 导入失败（年龄格式错）：" + e.getMessage());
        }
    }

    // Getter和Setter（用IDE生成所有属性的，包括maxRider、numOfCycles）
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = maxRider; }
    public int getNumOfCycles() { return numOfCycles; }
}