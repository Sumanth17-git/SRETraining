package com.looging.springbootmicroservice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.instrument.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class JsonResponseController {

    private static final Logger logger = LoggerFactory.getLogger(JsonResponseController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Counter requestCounter;
    private final Timer transactionTimer;
    private final Counter productViewCounter;

    public JsonResponseController(MeterRegistry registry) {
        this.requestCounter = registry.counter("api.json.requests.count");
        this.transactionTimer = registry.timer("api.json.transaction.timer");
        this.productViewCounter = registry.counter("api.product.views.count");
    }

    @GetMapping("/json")
    public Map<String, Object> getJsonResponse() {
        // Measure transaction duration
        return transactionTimer.record(() -> {
            requestCounter.increment();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Hello, World!");
            response.put("status", "success");
            response.put("data", getSampleUserData());
            response.put("products", getSampleProductData());

            try {
                String jsonResponse = objectMapper.writeValueAsString(response);
                logger.info("JSON Response: {}", jsonResponse);
            } catch (Exception e) {
                logger.error("Error converting response to JSON", e);
            }
            return response;
        });
    }

    @GetMapping("/product/{id}")
    public Map<String, Object> getProductDetails(@PathVariable int id) {
        productViewCounter.increment();
        Map<String, Object> product = new HashMap<>();
        product.put("id", id);
        product.put("name", RandomUserGenerator.getRandomProductName());
        product.put("description", RandomUserGenerator.getRandomProductDescription());
        product.put("price", RandomUserGenerator.getRandomPrice());
        product.put("availability", RandomUserGenerator.getRandomAvailability());
        logger.info("Product Viewed: {}", product);
        return product;
    }
    
    private Map<String, String> getSampleUserData() {
        Map<String, String> data = new HashMap<>();
        data.put("name", RandomUserGenerator.getRandomName());
        data.put("email", RandomUserGenerator.getRandomEmail());
        data.put("address", "123 Main St, Anytown, USA");
        return data;
    }

    private List<Map<String, Object>> getSampleProductData() {
        List<Map<String, Object>> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> product = new HashMap<>();
            product.put("id", RandomUserGenerator.getRandomId());
            product.put("name", RandomUserGenerator.getRandomProductName());
            product.put("description", RandomUserGenerator.getRandomProductDescription());
            product.put("price", RandomUserGenerator.getRandomPrice());
            product.put("availability", RandomUserGenerator.getRandomAvailability());
            products.add(product);
        }
        return products;
    }
}