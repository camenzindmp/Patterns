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
        private String newMeetingDate;
    }

    public static ClientInfo getClientInfo() {
        Faker faker = new Faker(new Locale("ru"));
        return new ClientInfo(
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber()
        );
    }

    public static ClientInfo generateCity() {
        private String[] cities = new String[]{"Москва", "Казань", "Владивосток", "Екатеринбург", "Воронеж"};
        private Random rand = new Random();
        public String getRandomCity () {
            return cities[rand.nextInt(4)];
        }
    }

    public static ClientInfo generateDate() {
        LocalDate meetingDate = LocalDate.now().plusDays(7);  // вычисление текущей даты + 7 дней;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate newMeetingDate = LocalDate.now().plusDays(9);  // вычисление текущей даты + 9 дней;
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return new ClientInfo(meetingDate);
    }
}

