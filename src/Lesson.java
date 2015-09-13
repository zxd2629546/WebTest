import java.util.ArrayList;
import java.util.HashSet;

public class Lesson {
	private String name;
	private String teacher;
	private String classroom;
	private ArrayList<Interval> times;
	private HashSet<Interval> weeks;
	
	public Lesson(String name, String teacher) {
		this.name = name;
		this.teacher = teacher;
		times = new ArrayList<Interval>();
		weeks = new HashSet<Interval>();
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTeacher() {
		return teacher;
	}


	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}


	public String getClassroom() {
		return classroom;
	}


	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}


	public ArrayList<Interval> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<Interval> times) {
		this.times = times;
	}

	public HashSet<Interval> getWeeks() {
		return weeks;
	}

	public void setWeeks(HashSet<Interval> weeks) {
		this.weeks = weeks;
	}
	
	public String toHtml() {
		StringBuilder builder = new StringBuilder();
		builder.append(name + "<br>" + teacher + "&nbsp" + classroom + "<br>");
		for (Interval interval : weeks) {
			builder.append(interval.toString() + " ");
		}
		builder.append("周");
		return  builder.toString();
	}
}
