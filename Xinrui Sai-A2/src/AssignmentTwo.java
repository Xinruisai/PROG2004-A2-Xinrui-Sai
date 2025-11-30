public class AssignmentTwo {
    // 程序入口（直接运行Part4B，无需修改）
    public static void main(String[] args) {
        AssignmentTwo test = new AssignmentTwo();
        test.partFourB();
    }

    // Part3 队列测试（保留，无需修改）
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

    // Part4A 历史测试（保留，无需修改）
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

    // ========== Part4B 排序测试（完整实现，无需添加） ==========
    public void partFourB() {
        System.out.println("=== Part4B 排序功能测试（Xinrui Sai） ===");
        Ride thunder = new Ride("雷霆战车", null);
        // 添加乱序测试游客
        thunder.addVisitorToHistory(new Visitor("A", 28, "T010", "2025-12-01"));
        thunder.addVisitorToHistory(new Visitor("B", 22, "T009", "2025-12-01"));
        thunder.addVisitorToHistory(new Visitor("C", 25, "T008", "2025-12-01"));
        thunder.addVisitorToHistory(new Visitor("D", 22, "T007", "2025-12-01"));

        // 排序前打印
        System.out.println("🔸 排序前：");
        thunder.printRideHistory();
        // 执行排序
        thunder.sortRideHistory();
        // 排序后打印
        System.out.println("🔸 排序后：");
        thunder.printRideHistory();
    }

    // 后续方法占位（无需修改）
    public void partFive() {}
    public void partSix() {}
    public void partSeven() {}
}