package r_okamot.coverage_collecter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TraceCollecter {

    static public List<String> collect(String reportHtml) {
        List<String> coverage = new ArrayList<String>();
        Document doc = Jsoup.parse(reportHtml);
        Elements spans = doc.getElementsByTag("span");
        for (Element span : spans) {
            if (!isTargetSpan(span))
                continue;
            String coveredType = span.attr("class");
            String lineNumber = span.attr("id").substring(1);
            if (isCovered(coveredType))
                coverage.add(lineNumber);
        }
        return coverage;
    }
    
    static private boolean isTargetSpan(Element span) {
        return span.hasAttr("class") && span.hasAttr("id");
    }
    
    static private boolean isCovered(String coveredType) {
        return coveredType.startsWith("fc") || coveredType.startsWith("pc");
    }
}
