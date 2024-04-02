package r_okamot.coverage_collecter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CoverageCollecter {
    private Path reportDir = Paths.get(".");
    
    public CoverageCollecter(String reportDir) {
        this.reportDir = Paths.get(reportDir);
    }
    
    public Coverage collect() throws IOException {
        Coverage coverage = new Coverage();
        List<Path> reportFiles = getReportFiles();
        for (Path reportFile : reportFiles) {
            String reportHtml = readAll(reportFile);
            List<String> trace = TraceCollecter.collect(reportHtml);
            String productFileName = extractProductFileName(reportFile);
            coverage.add(productFileName, trace);
        }
        return coverage;
    }

    private List<Path> getReportFiles() throws IOException {
        return Files.walk(reportDir)
                .map(path->path.toString())
                .filter(path->path.endsWith(".java.html"))
                .map(path->Paths.get(path))
                .toList();
    }
    
    private static String readAll(Path path) throws IOException {
        return Files.lines(path, Charset.forName("UTF-8"))
            .collect(Collectors.joining(System.getProperty("line.separator")));
    }
    
    private String extractProductFileName(Path reportFile) {
        int rootIndex = 0;
        int nameCount = reportFile.getNameCount();
        for (; rootIndex < nameCount; ++rootIndex) {
            String dir = reportFile.getName(rootIndex).toString();
            if (dir.equals("jacoco"))
                break;
        }
        return reportFile.subpath(rootIndex + 1, nameCount)
                    .toString()
                    .replace(".java.html", ".java")
                    .replace(System.getProperty("file.separator"), ".");
    }
}
