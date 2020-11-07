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

    @Data
    @AllArgsConstructor
    public static class ClientInfo {
        private String fullName;
        private String phoneNumber;
        private String city;
        private String meetingDate;
        private String formatter;
        private String newMeetingDate;
        private String newFormatter;
    }

    public static ClientInfo getClientInfo() {
        Faker faker = new Faker(new Locale("ru"));
        return new ClientInfo(
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                generateCity().city,
                generateMeetingDate().meetingDate,
                generateMeetingDate().formatter,
                generateNewMeetingDate().newMeetingDate,
                generateNewMeetingDate().newFormatter);
    }

    public static ClientInfo generateCity() {
        String[] cities = new String[] {"Москва", "Казань", "Владивосток", "Екатеринбург", "Воронеж"};
        Random generator = new Random();
        int randomIndex = generator.nextInt(cities.length);
        ClientInfo.city = cities[randomIndex];
        return new ClientInfo().city;
        }

    public static ClientInfo generateMeetingDate() {
        LocalDate meetingDate = LocalDate.now().plusDays(7);  // вычисление текущей даты + 7 дней;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return new ClientInfo().formatter;
    }

    public static ClientInfo generateNewMeetingDate() {
        LocalDate newMeetingDate = LocalDate.now().plusDays(9);  // вычисление текущей даты + 9 дней;
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return new ClientInfo().newFormatter;
    }

}

