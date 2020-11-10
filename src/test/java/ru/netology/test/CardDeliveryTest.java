package ru.netology.test;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.UserGenerator;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {

    @Test
    void successCase() {
        open("http://localhost:9999/");
        Object ClientInfo = UserGenerator.getClientInfo();
        $("[data-test-id=city] input[class=input__control]").setValue(UserGenerator.getClientInfo().getCity()); // берем рандомный город из массива CitiesArray;
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        $("[data-test-id=date] [type=tel]").setValue(UserGenerator.getClientInfo().getMeetingDate()); // ввод даты в нужном формате в инпут;
        $("[data-test-id=name] [type=text]").setValue(UserGenerator.getClientInfo().getFullName()); // ввод сгенерированного имени;
        $("[data-test-id=phone] [type=tel]").setValue(UserGenerator.getClientInfo().getPhoneNumber()); // ввод сгенерированного номера телефона;
        $("[data-test-id=agreement]").click(); // клик по чб;
        $("[type=button] [class='button__text']").click(); // клик по копке "Отправить";
//        $("[data-test-id=success-notification]").shouldBe(Condition.visible); // Проверка нтификейшена
        $(byText("Успешно!\nВстреча успешно запланирована на " + UserGenerator.getClientInfo().getMeetingDate().format(UserGenerator.getClientInfo().getFormatter()))).shouldBe(Condition.visible);
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        $("[data-test-id=date] [type=tel]").setValue(UserGenerator.getClientInfo().getNewMeetingDate()); // ввод даты в нужном формате в инпут;
        String successText = $("[data-test-id=success-notification]").getText(); // получить текст и дату из нотификейшена;
        assertEquals("Успешно!\nВстреча успешно запланирована на " + UserGenerator.getClientInfo().getNewMeetingDate().format(UserGenerator.getClientInfo().getNewFormatter()), successText); // сравнить текст и дату из нотификейшена с ожидаемым текстом и текущей датой;
    }
}