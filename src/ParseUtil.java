import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ParseUtil {
	public static final String TIME = "上课时间";
	public static final String POSITION = "上课地点";
	public static final String WEEK = "上课周次";
	
	public static void parseLesson(HtmlPage page, Lesson lesson){
		String content = page.asText();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes())));
		try{
			reader.readLine();
			reader.readLine();
			String line = null;
			while((line = reader.readLine()) != null) {
				if (line.startsWith(TIME)) {
					parseTime(line.substring(line.indexOf("：") + 1).trim(), lesson);
				} else if (line.startsWith(POSITION)) {
					lesson.setClassroom(line.substring(line.indexOf("：") + 1).trim());
				} else if (line.startsWith(WEEK)) {
					parseWeeks(line.substring(line.indexOf("：") + 1).trim(), lesson);
				}
			}
			//System.out.println(lesson.getTimes());
			//System.out.println(lesson.getClassroom());
			//System.out.println(lesson.getWeeks());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void parseTime(String time, Lesson lesson) {
		int res = 66;
		if (time.contains("一")) {
			res = 0;
		} else if(time.contains("二")){
			res = 11;
		} else if(time.contains("三")){
			res = 22;
		} else if(time.contains("四")){
			res = 33;
		} else if(time.contains("五")){
			res = 44;
		} else if(time.contains("六")){
			res = 55;
		}
		String sub = time.substring(time.indexOf("第") + 1, time.length() - 2);
		String[] parts = sub.split("、");
		int start = Integer.parseInt(parts[0]);
		lesson.getTimes().add(new Interval(res + start, res + start + parts.length - 1));
	}

	private static void parseWeeks(String weeks, Lesson lesson) {
		String[] parts = weeks.split("、");
		int st = Integer.parseInt(parts[0]);
		Set<Interval> set = lesson.getWeeks();
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
	}
}
