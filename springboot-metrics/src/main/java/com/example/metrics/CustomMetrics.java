package com.example.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class CustomMetrics {

    private final Counter requestCounter;
    private final Timer requestTimer;
    private final List<Double> gaugeList = new ArrayList<>();

    @Autowired
    public CustomMetrics(MeterRegistry meterRegistry) {
        // Define a counter metric
        this.requestCounter = Counter.builder("custom_requests_total")
                                     .description("Total requests")
                                     .register(meterRegistry);

        // Define a histogram/timer metric
        this.requestTimer = Timer.builder("custom_request_duration")
                                 .description("Duration of requests")
                                 .register(meterRegistry);

        // Define a gauge metric
        Gauge.builder("custom_gauge_value", gaugeList, List::size)
             .description("Custom gauge value")
             .register(meterRegistry);
    }

    // Method to increment counter
    public void incrementCounter() {
        requestCounter.increment();
    }

    // Method to record histogram/timer metric
    public void recordRequestDuration(long duration) {
        requestTimer.record(duration, TimeUnit.MILLISECONDS);
    }

    // Method to update gauge
    public void updateGauge(double value) {
        gaugeList.add(value);
    }

    // Method to remove a gauge value
    public void removeGaugeValue() {
        if (!gaugeList.isEmpty()) {
            gaugeList.remove(0);
        }
    }
}
