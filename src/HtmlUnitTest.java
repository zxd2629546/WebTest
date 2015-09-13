import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlListing;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class HtmlUnitTest {
	public static void main(String[] args) throws FailingHttpStatusCodeException, IOException, InterruptedException {
		String  url="http://sep.ucas.ac.cn/";//想采集的网址
        URL link=new URL(url); 
        WebClient wc=new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
        WebRequest request=new WebRequest(link); 

        wc.getCookieManager().setCookiesEnabled(true);//开启cookie管理
        wc.getOptions().setJavaScriptEnabled(true);//开启js解析。对于变态网页，这个是必须的
        wc.getOptions().setCssEnabled(true);//开启css解析。对于变态网页，这个是必须的。
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        wc.getOptions().setThrowExceptionOnScriptError(false);
        wc.getOptions().setTimeout(10000);

        HtmlPage page=null;
        page = wc.getPage(request);
        HtmlInput username = (HtmlInput)page.getElementById("userName");
        HtmlInput pwd = (HtmlInput)page.getElementById("pwd");
        HtmlButton button = (HtmlButton) page.getElementById("sb");
        
        username.setValueAttribute("964050436@qq.com");
        pwd.setValueAttribute("43102319940614241x");
        
        HtmlPage page2 = button.click();
        System.out.println(wc.getCookieManager().getCookies());
        //System.out.println(page2.asXml());
        
        HtmlPage page3 = page2.getAnchorByHref("/portal/site/226/821").click();
        System.out.println(wc.getCookieManager().getCookies());
        Thread.sleep(1000);
        //System.out.println(page3.asXml());
        
        HtmlAnchor anchor = page3.getAnchorByText("这里");
        HtmlPage page4 = anchor.click();
        System.out.println(wc.getCookieManager().getCookies());
        Thread.sleep(1000);
        //System.out.println(page4.asXml());
        
        //http://jwjz.ucas.ac.cn/Student/DeskTopModules/Left.aspx
        WebRequest request2 =new WebRequest(new URL("http://jwjz.ucas.ac.cn/Student/DeskTopModules/Left.aspx"));
        HtmlPage page5 = wc.getPage(request2);
        System.out.println(wc.getCookieManager().getCookies());
        //System.out.println(page5.asXml());
        HtmlPage page6 = page5.getAnchorByText("选择课程").click();
        System.out.println(wc.getCookieManager().getCookies());
        //System.out.println(page6.asXml());
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        for (int i = 2;;i ++) {
        	HtmlAnchor anchor2 = (HtmlAnchor) page6.getElementById("CourseList__ctl" + i + "_NameLink");
        	if (anchor2 == null) break;
        	String name = anchor2.asText();
        	String teacher = anchor2.getParentNode().getParentNode().getChildNodes().get(7).asText();
        	Lesson lesson = new Lesson(name, teacher);
        	HtmlPage page7 = page6.getAnchorByHref(anchor2.getHrefAttribute()).click();
        	ParseUtil.parseLesson(page7, lesson);
        	lessons.add(lesson);
        	System.out.println();
        }
        FileWriter writer = new FileWriter("lessons.html");
        writer.write(HtmlUtil.lesson2Html(lessons));
        writer.close();
	}
}
