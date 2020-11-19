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
        UserGenerator.ClientInfo clientInfo = UserGenerator.getClientInfo();
        $("[data-test-id=city] input[class=input__control]").setValue(clientInfo.getCity()); // берем рандомный город из массива CitiesArray;
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        $("[data-test-id=date] [type=tel]").setValue(UserGenerator.generateMeetingDate()); // ввод даты в нужном формате в инпут;
        $("[data-test-id=name] [type=text]").setValue(clientInfo.getFullName()); // ввод сгенерированного имени;
        $("[data-test-id=phone] [type=tel]").setValue(clientInfo.getPhoneNumber()); // ввод сгенерированного номера телефона;
        $("[data-test-id=agreement]").click(); // клик по чб;
        $("[type=button] [class='button__text']").click(); // клик по копке "Отправить";
        $(byText("Успешно!\nВстреча успешно запланирована на " + UserGenerator.generateMeetingDate())).shouldBe(Condition.visible); // проверка 1-го нотификейшена;
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        $("[data-test-id=date] [type=tel]").setValue(UserGenerator.generateMeetingDate()); // ввод даты в нужном формате в инпут;
        $(byText("Успешно!\nВстреча успешно запланирована на " + UserGenerator.generateMeetingDate())).shouldBe(Condition.visible); // проверка 2-го нотификейшена;
    }
}