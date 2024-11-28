package com.looging.springbootmicroservice;

import java.util.Random;

public class RandomUserGenerator {

    private static final String[] FIRST_NAMES = {
        "John", "Jane", "Alex", "Chris", "Pat", "Taylor", "Jordan", "Casey"
    };

    private static final String[] LAST_NAMES = {
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"
    };

    private static final String[] EMAIL_DOMAINS = {
        "example.com", "mail.com", "test.com", "demo.com"
    };

    private static final String[] PRODUCT_NAMES = {
        "Laptop", "Smartphone", "Tablet", "Headphones", "Smartwatch", "Camera"
    };

    private static final String[] PRODUCT_DESCRIPTIONS = {
        "High performance", "Latest model", "Affordable price", "Top quality", "Best seller", "User friendly"
    };

    private static final Random RANDOM = new Random();

    public static String getRandomName() {
        String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }

    public static String getRandomEmail() {
        String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)].toLowerCase();
        String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)].toLowerCase();
        String domain = EMAIL_DOMAINS[RANDOM.nextInt(EMAIL_DOMAINS.length)];
        return firstName + "." + lastName + "@" + domain;
    }

    public static String getRandomProductName() {
        return PRODUCT_NAMES[RANDOM.nextInt(PRODUCT_NAMES.length)];
    }

    public static String getRandomProductDescription() {
        return PRODUCT_DESCRIPTIONS[RANDOM.nextInt(PRODUCT_DESCRIPTIONS.length)];
    }

    public static double getRandomPrice() {
        return 50 + (5000 - 50) * RANDOM.nextDouble(); // price range from $50 to $5000
    }

    public static boolean getRandomAvailability() {
        return RANDOM.nextBoolean();
    }

    public static int getRandomId() {
        return RANDOM.nextInt(1000); // random ID from 0 to 999
    }
}
