import java.util.HashSet;
import java.util.Set;

public class IntervalTest {
	public static void main(String[] args) {
		String[] parts = "2、3、4、5、6、7、8、9、10、11、12、13".split("、");
		int st = Integer.parseInt(parts[0]);
		Set<Interval> set = new HashSet<Interval>();
		for (int i = 1;i < parts.length;i ++) {
			int now = Integer.parseInt(parts[i]);
			int bef = Integer.parseInt(parts[i - 1]);
			if (now != bef + 1) {
				set.add(new Interval(st, bef));
				st = now;
			} else if(i == parts.length - 1) {
				set.add(new Interval(st, now));
			}
		}
		System.out.println(set);
	}
}
