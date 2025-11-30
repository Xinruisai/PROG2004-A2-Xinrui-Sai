import java.util.Comparator;

// Xinrui Sai 专属：按年龄升序，年龄相同按票号升序
public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 第一步：按年龄升序
        int ageCompare = Integer.compare(v1.getAge(), v2.getAge());
        if (ageCompare != 0) {
            return ageCompare;
        }
        // 第二步：年龄相同，按票号升序
        return v1.getTicketId().compareTo(v2.getTicketId());
    }
}