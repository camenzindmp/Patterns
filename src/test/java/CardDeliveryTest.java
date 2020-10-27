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
        $("[data-test-id=city] input[class=input__control]").setValue(faker.address().city()); // Изменились требовнаия  к городам (Новокузнецк, Сочи, Тольяти)
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        LocalDate meetingDate = LocalDate.now().plusDays(7);  // вычисление текущей даты + 7 дней;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // перевод даты в нужный формат;
        $("[data-test-id=date] [type=tel]").setValue(meetingDate.format(formatter)); // ввод даты в нужном формате в инпут;
        $("[data-test-id=name] [type=text]").setValue(faker.name().fullName());
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("ru"), new RandomService());
        $("[data-test-id=phone] [type=tel]").setValue(fakeValuesService.numerify("7##########"));
        $("[data-test-id=agreement]").click();
        $("[type=button] [class='button__text']").click();
        $("[data-test-id=success-notification]").waitUntil(Condition.visible, 3000);
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        LocalDate meetingDate2 = LocalDate.now().plusDays(9);  // вычисление текущей даты + 7 дней;
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // перевод даты в нужный формат;
        $("[data-test-id=date] [type=tel]").setValue(meetingDate2.format(formatter2)); // ввод даты в нужном формате в инпут;
        String successText = $("[data-test-id=success-notification]").getText(); // получить текст и дату из нотификейшена;
        assertEquals("Успешно!\nВстреча успешно запланирована на " + meetingDate2.format(formatter2), successText); // сравнить текст и дату из нотификейшена с ожидаемым текстом и текущей датой;
    }
}