public class AssignmentTwo {
    // 程序入口
    public static void main(String[] args) {
        AssignmentTwo test = new AssignmentTwo();
        // test.partThree(); // 注释Part3，运行Part4A
        test.partFourA();
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

    // ========== Part4A 历史测试（新增完整方法） ==========
    public void partFourA() {
        System.out.println("=== Part4A 历史功能测试（Xinrui Sai） ===");
        // 创建骑行项目
        Ride thunder = new Ride("雷霆战车", null);
        // 创建测试游客
        Visitor v1 = new Visitor("马九", 24, "T006", "2025-12-01");
        Visitor v2 = new Visitor("胡十", 32, "T007", "2025-12-01");
        Visitor v3 = new Visitor("吴十一", 28, "T008", "2025-12-01");
        // 1. 添加游客到历史
        thunder.addVisitorToHistory(v1);
        thunder.addVisitorToHistory(v2);
        thunder.addVisitorToHistory(v3);
        // 2. 检查某游客是否在历史
        thunder.checkVisitorFromHistory(v1);
        thunder.checkVisitorFromHistory(new Visitor("不存在", 20, "T999", "2025-12-01"));
        // 3. 统计历史游客数
        thunder.numberOfVisitors();
        // 4. 打印历史
        thunder.printRideHistory();
    }

    // 后续方法占位
    public void partFourB() {}
    public void partFive() {}
    public void partSix() {}
    public void partSeven() {}
}