package utils;

import com.github.javafaker.Faker;

public final class RandomUtils {
    private static final Faker faker = new Faker();

    private RandomUtils() {
    }

    public static String generateCity(String state) {
        switch (state) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");
            default:
                throw new IllegalArgumentException("Unknown state: " + state);
        }
    }

    public static String generatePhoneNumber(int length) {
        return faker.number().digits(length);
    }

}
