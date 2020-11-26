package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class UserGenerator {

    private UserGenerator() {
    }

    public static String generateMeetingDate(int shift) {
        LocalDate meetingDate = LocalDate.now().plusDays(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return meetingDate.format(formatter);
    }

    public static String generateCity() {
        String[] cities = new String[]{"Москва", "Казань", "Владивосток", "Екатеринбург", "Воронеж"};
        Random generator = new Random();
        int randomIndex = generator.nextInt(cities.length);
        return cities[randomIndex];
    }

    @Data
    @AllArgsConstructor
    public static class ClientInfo {
        private String fullName;
        private String phoneNumber;
        private String city;
    }

    public static ClientInfo getClientInfo() {
        Faker faker = new Faker(new Locale("ru"));
        return new ClientInfo(
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                generateCity());
    }
}

