import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator() {
    }

    static class ClientInfo {
        public ClientInfo(String city, String fullName, String numerify, LocalDate plusDays) {
        }

        public ClientInfo(String fullName, String phoneNumber, LocalDate plusDays) {
        }
    }

    public class CitiesArray {
        private String[] cities = new String[]{"Москва","Казань", "Владивосток", "Екатеринбург", "Воронеж"};
        private Random rand = new Random();
        public String getRandomCity(){
            return cities[rand.nextInt(4)];
        }
    }

    public static class CardDeliveryForm {
        private CardDeliveryForm() {
        }

        public static ClientInfo generateByClient(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new ClientInfo(
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber(),
                    LocalDate.now().plusDays(7)
            );
        }
    }
}
//
//In your Java code:
//
//        Faker faker = new Faker();
//
//        String name = faker.name().fullName(); // Miss Samanta Schmidt
//        String firstName = faker.name().firstName(); // Emory
//        String lastName = faker.name().lastName(); // Barton
//
//        String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
//    }
//}
