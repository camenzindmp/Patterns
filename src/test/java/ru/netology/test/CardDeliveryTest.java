package ru.netology.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.UserGenerator.*;

public class CardDeliveryTest {

    @Test
    void cardDelivery() {
        open("http://localhost:9999/");
        ClientInfo clientInfo = getClientInfo();
        $("[data-test-id=city] input[class=input__control]").setValue(clientInfo.getCity()); // берем рандомный город из массива CitiesArray;
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        $("[data-test-id=date] [type=tel]").setValue(generateMeetingDate(7)); // ввод даты в нужном формате в инпут;
        $("[data-test-id=name] [type=text]").setValue(clientInfo.getFullName()); // ввод сгенерированного имени;
        $("[data-test-id=phone] [type=tel]").setValue(clientInfo.getPhoneNumber()); // ввод сгенерированного номера телефона;
        $("[data-test-id=agreement]").click(); // клик по чб;
        $("[type=button] [class='button__text']").click(); // клик по копке "Запланировать";
        $(byText("Успешно!")).shouldBe(visible); // проверка 1-го нотификейшена;
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на " + generateMeetingDate(7)));
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        $("[data-test-id=date] [type=tel]").setValue(generateMeetingDate(9)); // ввод даты в нужном формате в инпут;
        $("[type=button] [class='button__text']").click(); // клик по копке "Запланировать";
        $(byText("Необходимо подтверждение")).shouldBe(visible); // проверка нотификейшена о перепланировании даты;
        $(byText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible); // проверка нотификейшена о перепланировании даты;
        $("[data-test-id='replan-notification'] [type=button]").click(); // клик по копке "Перепланировать";
        $(byText("Успешно!")).shouldBe(visible); // проверка 2-го нотификейшена;
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на " + generateMeetingDate(9)));
    }
}

// для запуска сборки в CI