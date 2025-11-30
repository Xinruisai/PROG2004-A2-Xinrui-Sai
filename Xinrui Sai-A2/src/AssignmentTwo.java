public class AssignmentTwo {
    // 程序入口（直接运行这个方法）
    public static void main(String[] args) {
        AssignmentTwo test = new AssignmentTwo();
        test.partThree(); // 只运行Part3测试
    }

    // Part3 队列测试完整方法（直接复制，无需修改）
    public void partThree() {
        System.out.println("=== Part3 队列功能测试（Xinrui Sai） ===");
        // 1. 创建操作员
        Employee operator = new Employee("张三", 30, "440101199501011234", "EMP001", "骑行操作员");
        // 2. 创建骑行项目（过山车）
        Ride rollerCoaster = new Ride("过山车", operator);
        // 3. 创建5个测试游客
        Visitor v1 = new Visitor("李四", 25, "T001", "2025-12-01");
        Visitor v2 = new Visitor("王五", 18, "T002", "2025-12-01");
        Visitor v3 = new Visitor("赵六", 35, "T003", "2025-12-01");
        Visitor v4 = new Visitor("陈七", 22, "T004", "2025-12-01");
        Visitor v5 = new Visitor("周八", 28, "T005", "2025-12-01");
        // 4. 添加游客到队列
        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);
        // 5. 打印初始队列
        rollerCoaster.printQueue();
        // 6. 移除1个游客
        rollerCoaster.removeVisitorFromQueue();
        // 7. 打印移除后的队列
        rollerCoaster.printQueue();
    }

    // 后续方法占位（保证类不报错）
    public void partFourA() {}
    public void partFourB() {}
    public void partFive() {}
    public void partSix() {}
    public void partSeven() {}
}