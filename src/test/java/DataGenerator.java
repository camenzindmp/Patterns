import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class CardDeliveryForm {
        private CardDeliveryForm() {
        }

        public static ClientInfo generateByClient(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            FakeValuesService fakeValuesService = new FakeValuesService(
                    new Locale("ru"), new RandomService());
            return new ClientInfo(
                    faker.address().city(),
                    faker.name().fullName(),
                    fakeValuesService.numerify("7##########"),
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
