/**
 * GenAI Declaration (Xinrui Sai)
 * I, Xinrui Sai, acknowledge that I have not knowingly used GenAI to complete this PROG2004 A2 assessment.
 */
public class AssignmentTwo {
    // ========== 完整main方法（直接运行，调用Part7导入） ==========
    public static void main(String[] args) {
        AssignmentTwo test = new AssignmentTwo();
        test.partSeven(); // 运行CSV导入测试
    }

    // Part3 队列测试（保留）
    public void partThree() {
        System.out.println("=== Part3 队列功能测试（Xinrui Sai） ===");
        Employee operator = new Employee("张三", 30, "440101199501011234", "EMP001", "骑行操作员");
        Ride rollerCoaster = new Ride("过山车", operator);
        Visitor v1 = new Visitor("李四", 25, "T001", "2025-12-01");
        Visitor v2 = new Visitor("王五", 18, "T002", "2025-12-01");
        Visitor v3 = new Visitor("赵六", 35, "T003", "2025-12-01");
        Visitor v4 = new Visitor("陈七", 22, "T004", "2025-12-01");
        Visitor v5 = new Visitor("周八", 28, "T005", "2025-12-01");
        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);
        rollerCoaster.printQueue();
        rollerCoaster.removeVisitorFromQueue();
        rollerCoaster.printQueue();
    }

    // Part4A 历史测试（保留）
    public void partFourA() {
        System.out.println("=== Part4A 历史功能测试（Xinrui Sai） ===");
        Ride thunder = new Ride("雷霆战车", null);
        Visitor v1 = new Visitor("马九", 24, "T006", "2025-12-01");
        Visitor v2 = new Visitor("胡十", 32, "T007", "2025-12-01");
        Visitor v3 = new Visitor("吴十一", 28, "T008", "2025-12-01");
        thunder.addVisitorToHistory(v1);
        thunder.addVisitorToHistory(v2);
        thunder.addVisitorToHistory(v3);
        thunder.checkVisitorFromHistory(v1);
        thunder.checkVisitorFromHistory(new Visitor("不存在", 20, "T999", "2025-12-01"));
        thunder.numberOfVisitors();
        thunder.printRideHistory();
    }

    // Part4B 排序测试（保留）
    public void partFourB() {
        System.out.println("=== Part4B 排序功能测试（Xinrui Sai） ===");
        Ride thunder = new Ride("雷霆战车", null);
        thunder.addVisitorToHistory(new Visitor("A", 28, "T010", "2025-12-01"));
        thunder.addVisitorToHistory(new Visitor("B", 22, "T009", "2025-12-01"));
        thunder.addVisitorToHistory(new Visitor("C", 25, "T008", "2025-12-01"));
        thunder.addVisitorToHistory(new Visitor("D", 22, "T007", "2025-12-01"));
        System.out.println("🔸 排序前：");
        thunder.printRideHistory();
        thunder.sortRideHistory();
        System.out.println("🔸 排序后：");
        thunder.printRideHistory();
    }

    // Part5 骑行周期测试（保留）
    public void partFive() {
        System.out.println("=== Part5 骑行周期测试（Xinrui Sai） ===");
        Employee op = new Employee("张三", 30, "440101199501011234", "EMP001", "过山车操作员");
        Ride roller = new Ride("过山车", op, 2);
        roller.addVisitorToQueue(new Visitor("游客1", 20, "T001", "2025-12-01"));
        roller.addVisitorToQueue(new Visitor("游客2", 21, "T002", "2025-12-01"));
        roller.addVisitorToQueue(new Visitor("游客3", 22, "T003", "2025-12-01"));
        roller.addVisitorToQueue(new Visitor("游客4", 23, "T004", "2025-12-01"));
        roller.addVisitorToQueue(new Visitor("游客5", 24, "T005", "2025-12-01"));
        roller.addVisitorToQueue(new Visitor("游客6", 25, "T006", "2025-12-01"));
        System.out.println("🔸 初始队列：");
        roller.printQueue();
        roller.runOneCycle();
        System.out.println("\n🔸 运行后队列：");
        roller.printQueue();
        System.out.println("🔸 运行后历史：");
        roller.printRideHistory();
    }

    // Part6 CSV导出测试（保留）
    public void partSix() {
        System.out.println("=== Part6 CSV导出测试（Xinrui Sai） ===");
        Ride roller = new Ride("过山车", null, 2);
        roller.addVisitorToHistory(new Visitor("游客1", 20, "T001", "2025-12-01"));
        roller.addVisitorToHistory(new Visitor("游客2", 21, "T002", "2025-12-01"));
        roller.addVisitorToHistory(new Visitor("游客3", 22, "T003", "2025-12-01"));
        roller.exportRideHistory();
    }

    // ========== Part7 CSV导入测试（完整实现） ==========
    public void partSeven() {
        System.out.println("=== Part7 CSV导入测试（Xinrui Sai） ===");
        // 1. 先导出一份测试数据（确保桌面有CSV文件）
        Ride roller = new Ride("过山车", null, 2);
        roller.addVisitorToHistory(new Visitor("游客1", 20, "T001", "2025-12-01"));
        roller.addVisitorToHistory(new Visitor("游客2", 21, "T002", "2025-12-01"));
        roller.addVisitorToHistory(new Visitor("游客3", 22, "T003", "2025-12-01"));
        roller.exportRideHistory();

        // 2. 清空历史后导入CSV文件
        roller.importRideHistory();

        // 3. 验证导入结果
        System.out.println("\n🔸 导入后历史记录：");
        roller.printRideHistory();
        System.out.println("🔸 导入后游客总数：");
        roller.numberOfVisitors();
    }
}