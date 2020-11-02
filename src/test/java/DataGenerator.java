import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator() {
    }

    static class ClientInfo {
        public ClientInfo(String fullName, String phoneNumber1) {
        }
    }

    public class CitiesArray {
        private String[] cities = new String[]{"Москва", "Казань", "Владивосток", "Екатеринбург", "Воронеж"};
        private Random rand = new Random();

        public String getRandomCity() {
            return cities[rand.nextInt(4)];
        }
    }

    public static DateGenerator {
        LocalDate MeetingDate = LocalDate.now().plusDays(7);  // вычисление текущей даты + 7 дней;
        DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate newMeetingDate = LocalDate.now().plusDays(9);  // вычисление текущей даты + 9 дней;
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    public static class CardDeliveryForm {
        private CardDeliveryForm() {
        }

        public static ClientInfo generateByClient(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new ClientInfo(
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber()
            );
        }
    }
}
//}
