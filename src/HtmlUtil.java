import java.util.List;

public class HtmlUtil {
	public static String lesson2Html(List<Lesson> lessons){
		StringBuilder builder = new StringBuilder();
		setHead(builder);
		
		for (int i = 1;i <= 11;i ++) {
			builder.append("<tr height=50dp>\n");
			builder.append("<td style=\"width:2%\">" + i +"</td>");
			for (int j = i;j <= 77; j += 11) {
				int flag = 0;
				for (Lesson lesson : lessons) {
					List<Interval> times = lesson.getTimes();
					for (Interval interval : times) {
						if (interval.st == j) {
							flag = 1;
							builder.append("<td rowspan=" + interval.length());
							builder.append("><font size=2>" + lesson.toHtml() + "</font>");
						} else if (interval.st < j && j <= interval.ed) {
							flag = 2;
						}
					}
				}
				if(flag < 2){
					if (flag == 0)builder.append("<td>");
					builder.append("</td>\n");
				}
			}
		}
		
		builder.append("</table>\n</body>\n</html>");
		return builder.toString();
	}
	
	public static void setHead(StringBuilder builder){
		builder.append("<html>\n<head>\n<meta charset=\"utf-8\" />\n<style>\n");
		builder.append(".lesson{height:80dp}\ntd{width:14%;align:center;text-align:center}\n");
		builder.append("</style>\n</head>\n<body>\n");
		builder.append("<table style=\"border-style:solid; border-width:1; border-collapse: collapse\" bordercolor=\"#000000\""
				+ " border=\"2\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#C0C0C0\"  width=65%  align=center bordercolor=black cellspacing=0 cellpadding=1>");
		builder.append("<tr><td style=\"width:2%\"></td>\n");
		builder.append("<td><font size=2>星期一</font></td>\n");
		builder.append("<td><font size=2>星期二</font></td>\n");
		builder.append("<td><font size=2>星期三</font></td>\n");
		builder.append("<td><font size=2>星期四</font></td>\n");
		builder.append("<td><font size=2>星期五</font></td>\n");
		builder.append("<td><font size=2>星期六</font></td>\n");
		builder.append("<td><font size=2>星期日</font></td>\n</tr>\n");
	}
}
