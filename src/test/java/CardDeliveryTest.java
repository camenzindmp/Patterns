import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {
    private Faker faker;

    @BeforeEach
    void setUp() {
        faker = new Faker(new Locale("ru"));
    }

    @Test
    void successCase() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue(faker.address().city());
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        LocalDate meetingDate = LocalDate.now().plusDays(7);  // вычисление текущей даты + 7 дней;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // перевод даты в нужный формат;
        $("[data-test-id=date] [type=tel]").setValue(meetingDate.format(formatter)); // ввод даты в нужном формате в инпут;
        $("[data-test-id=name] [type=text]").setValue(faker.name().fullName());
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("ru"), new RandomService());
        $("[data-test-id=phone] [type=tel]").setValue(fakeValuesService.numerify("###########"));
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000);
        String successText = $("[data-test-id=notification]").getText(); // получить текст и дату из нотификейшена;
        assertEquals("Успешно!\nВстреча успешно забронирована на " + meetingDate.format(formatter), successText); // сравнить текст и дату из нотификейшена с ожидаемым текстом и текущей датой;
    }
}