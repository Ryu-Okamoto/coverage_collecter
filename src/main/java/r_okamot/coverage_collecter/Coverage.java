package r_okamot.coverage_collecter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Coverage {
    private Map<String, List<String>> fileTraceMap = new HashMap<>();
    
    public Set<String> keySet() {
        return fileTraceMap.keySet();
    }
    public List<String> get(String fileName) {
        if (fileTraceMap.containsKey(fileName))
            return fileTraceMap.get(fileName);
        return new ArrayList<String>();
    }
    
    public void add(String productFileName, List<String> trace) {
        fileTraceMap.put(productFileName, trace);
    }
}
