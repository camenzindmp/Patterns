//import com.sun.security.ntlm.Client;
//import org.junit.jupiter.api.BeforeEach;
//
//import java.util.Locale;
//
//public class DataGenerator {
//    private DataGenerator() {
//    }
//
//    public static class CardDeliveryForm {
//        private CardDeliveryForm() {
//        }
//    }
//
//    public static ClientData generateByCard(String locale) {
//        Faker faker = new Faker(new Locale("ru"));
//        return new ClientData(
//                faker.name().fullName()
//        );
//
//In your Java code
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
