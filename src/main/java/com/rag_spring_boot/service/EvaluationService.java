package com.rag_spring_boot.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class EvaluationService {

    private long totalQueries = 0;
    private long totalResponseTime = 0;
    private long safeQueries = 0;
    private long unsafeQueries = 0;

    public Map<String, Object> evaluate(String query, String route, String response, long startTime, boolean isSafe) {
        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        // ===== UPDATE STATISTIK =====
        totalQueries++;
        totalResponseTime += responseTime;

        if (isSafe) {
            safeQueries++;
        } else {
            unsafeQueries++;
        }

        // ===== METRIK =====
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("query_length", query != null ? query.length() : 0);
        metrics.put("response_length", response != null ? response.length() : 0);
        metrics.put("response_time_ms", responseTime);
        metrics.put("route", route != null ? route : "blocked");
        metrics.put("safe", isSafe);

        return metrics;
    }

    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total_queries", totalQueries);
        stats.put("average_response_time_ms", totalQueries > 0 ? totalResponseTime / totalQueries : 0);
        stats.put("safe_queries", safeQueries);
        stats.put("unsafe_queries", unsafeQueries);
        return stats;
    }
}
