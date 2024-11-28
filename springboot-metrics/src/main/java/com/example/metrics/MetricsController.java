package com.example.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    private final CustomMetrics customMetrics;

    @Autowired
    public MetricsController(CustomMetrics customMetrics) {
        this.customMetrics = customMetrics;
    }

    @GetMapping("/increment-counter")
    public String incrementCounter() {
        customMetrics.incrementCounter();
        return "Counter incremented";
    }

    @GetMapping("/record-duration")
    public String recordDuration() {
        // Simulate a random duration between 100ms and 1000ms
        long duration = (long) (Math.random() * 900 + 100);
        customMetrics.recordRequestDuration(duration);
        return "Duration recorded: " + duration + " ms";
    }

    @GetMapping("/update-gauge")
    public String updateGauge() {
        double value = Math.random() * 100;
        customMetrics.updateGauge(value);
        return "Gauge updated with value: " + value;
    }

    @GetMapping("/remove-gauge-value")
    public String removeGaugeValue() {
        customMetrics.removeGaugeValue();
        return "Gauge value removed";
    }
}
