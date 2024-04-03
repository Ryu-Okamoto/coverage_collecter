package r_okamot.coverage_collecter;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class TraceCollecterTest {
    
    @Test
    public void testNoSpans() {
        String reportHtml = ""
                + "           setDefaultFullDetail(true);\r\n"
                + "           setArrayContentDetail(true);\r\n"
                + "           setUseClassName(true);";
        List<String> expected = Arrays.asList();
        assertIterableEquals(expected, TraceCollecter.collect(reportHtml));
    }
    
    @Test
    public void testVariousWidthLineNunber() {
        String reportHtml = ""
                + "<span class=\"fc\" id=\"L5\">            setDefaultFullDetail(true);</span>\r\n"
                + "<span class=\"pc\" id=\"L56000\">            setArrayContentDetail(true);</span>\r\n"
                + "<span class=\"nc\" id=\"L597\">            setUseClassName(true);</span>";
        List<String> expected = Arrays.asList("5", "56000");
        assertIterableEquals(expected, TraceCollecter.collect(reportHtml));
    }
    
    @Test
    public void testSimpleReportHtml() {
        String reportHtml = ""
                + "<span class=\"fc\" id=\"L55\">            setDefaultFullDetail(true);</span>\r\n"
                + "<span class=\"pc\" id=\"L56\">            setArrayContentDetail(true);</span>\r\n"
                + "<span class=\"nc\" id=\"L57\">            setUseClassName(true);</span>";
        List<String> expected = Arrays.asList("55", "56");
        assertIterableEquals(expected, TraceCollecter.collect(reportHtml));
    }
    
    @Test
    public void testWithUnnecessaryAttrs() {
        String reportHtml = ""
                + "<span class=\"fc bnc\" id=\"L55\">            setDefaultFullDetail(true);</span>\r\n"
                + "<span class=\"pc bnc\" id=\"L56\">            setArrayContentDetail(true);</span>\r\n"
                + "<span class=\"nc bnc\" id=\"L57\">            setUseClassName(true);</span>";
        List<String> expected = Arrays.asList("55", "56");
        assertIterableEquals(expected, TraceCollecter.collect(reportHtml));
    }
}
