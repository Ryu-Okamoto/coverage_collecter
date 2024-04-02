package r_okamot.coverage_collecter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class App  {
    
    public static void main(String[] args) {
        
        if (args.length != 4) {
            System.out.println("usage: java -jar covergae_collecter.jar <test method name> <test result> <jacoco report dir> <output dir>");
            System.out.println("  where <test result> is 0(pass) or 1(fail).");
            return;
        }
        String testMethodName = args[0];
        String testResult = args[1];
        String jacocoReportDir = args[2];
        String outputDir = args[3];
        
        try {
            Coverage coverage = new CoverageCollecter(jacocoReportDir).collect();
            String summary = summaryCoverage(coverage);
            outputSummary(testMethodName, testResult, summary, outputDir);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private static String summaryCoverage(Coverage coverage) {
        String summary = "";
        for (String productFileName : coverage.keySet()) {
            summary += productFileName;
            summary += ":";
            summary += String.join(",", coverage.get(productFileName));
            summary += "\n";
        }
        return summary;
    }
    
    private static void outputSummary(String testMethodName, String testResult, String summary, String outputDir) throws IOException {
        if (!Files.exists(Paths.get(outputDir)))
            Files.createDirectories(Paths.get(outputDir));
        String outputFileName = testMethodName + ".cov";
        Path outputPath = Paths.get(outputDir, outputFileName);
        BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardOpenOption.CREATE);
        writer.write(testResult);
        writer.write('\n');
        writer.write(summary);
        writer.close();
    }
}
