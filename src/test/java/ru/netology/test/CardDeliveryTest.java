package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.UserGenerator;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void cardDelivery() {
        open("http://localhost:9999/");
        UserGenerator.ClientInfo clientInfo = UserGenerator.getClientInfo();
        $("[data-test-id=city] input[class=input__control]").setValue(clientInfo.getCity()); // берем рандомный город из массива CitiesArray;
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        $("[data-test-id=date] [type=tel]").setValue(UserGenerator.generateMeetingDate(7)); // ввод даты в нужном формате в инпут;
        $("[data-test-id=name] [type=text]").setValue(clientInfo.getFullName()); // ввод сгенерированного имени;
        $("[data-test-id=phone] [type=tel]").setValue(clientInfo.getPhoneNumber()); // ввод сгенерированного номера телефона;
        $("[data-test-id=agreement]").click(); // клик по чб;
        $("[type=button] [class='button__text']").click(); // клик по копке "Запланировать";
        $(byText("Успешно!")).shouldBe(Condition.visible); // проверка 1-го нотификейшена;
        $(byText("Встреча успешно запланирована на" )).shouldBe(Condition.visible); // проверка 1-го нотификейшена;
        $(byText(UserGenerator.generateMeetingDate(7))).shouldBe(Condition.visible); // проверка 1-го нотификейшена;
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        $("[data-test-id=date] [type=tel]").setValue(UserGenerator.generateMeetingDate(9)); // ввод даты в нужном формате в инпут;
        $("[type=button] [class='button__text']").click(); // клик по копке "Запланировать";
        $(byText("Необходимо подтверждение")).shouldBe(Condition.visible); // проверка нотификейшена о перепланировании даты;
        $(byText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible); // проверка нотификейшена о перепланировании даты;
        $("[data-test-id='replan-notification'] [type=button]").click(); // клик по копке "Перепланировать";
        $(byText("Успешно!")).shouldBe(Condition.visible); // проверка 2-го нотификейшена;
        $(byText("Встреча успешно запланирована на" )).shouldBe(Condition.visible); // проверка 2-го нотификейшена;
        $(byText(UserGenerator.generateMeetingDate(9))).shouldBe(Condition.visible); // проверка 2-го нотификейшена;
    }
}