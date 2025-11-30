public interface RideInterface {
    // Part3队列相关方法
    void addVisitorToQueue(Visitor visitor); // 添加游客到队列
    void removeVisitorFromQueue(); // 移除队列游客
    void printQueue(); // 打印队列

    // Part4历史相关方法
    void addVisitorToHistory(Visitor visitor); // 添加游客到历史
    boolean checkVisitorFromHistory(Visitor visitor); // 检查游客是否在历史
    int numberOfVisitors(); // 统计历史游客数
    void printRideHistory(); // 打印历史

    // Part5骑行周期方法
    void runOneCycle(); // 运行单次骑行
}
