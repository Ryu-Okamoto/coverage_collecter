package r_okamot.coverage_collecter;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CoverageCollecterTest {
    
    @Test
    public void testApplyItself() {
        String reportDir = "self_jacoco_report";
        CoverageCollecter collecter = new CoverageCollecter(reportDir);
        Coverage coverage;
        try {
            coverage = collecter.collect();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
            return;
        }
        
        List<String> traceInApp = Arrays.asList();
        assertIterableEquals(traceInApp, coverage.get("r_okamot.coverage_collecter.App.java"));
        
        List<String> traceInCoverage = Arrays.asList("9", "10", "22", "23");
        assertIterableEquals(traceInCoverage, coverage.get("r_okamot.coverage_collecter.Coverage.java"));
        
        List<String> traceInTraceCollecter = Arrays.asList("14","15","16","17","18","19","20","21","22","23","24","25","29","33");
        assertIterableEquals(traceInTraceCollecter, coverage.get("r_okamot.coverage_collecter.TraceCollecter.java"));
        
        List<String> traceInCoverageCollecter = Arrays.asList("12","14","15","16","19","20","21","22","23","24","25","26","27","31","32","33","34","35","39","40","44","45","46","47","48","49","51","52","53","54");
        assertIterableEquals(traceInCoverageCollecter, coverage.get("r_okamot.coverage_collecter.CoverageCollecter.java"));
    }
}
