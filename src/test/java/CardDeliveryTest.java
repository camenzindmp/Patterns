import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {

    @Test
    void successCase() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input[class=input__control]").setValue((String) DataGenerator.CardDeliveryForm.CitiesArray); // берем рандомный город из массива CitiesArray;
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        $("[data-test-id=date] [type=tel]").setValue(DateGenerator.generateDate); // ввод даты в нужном формате в инпут;
        $("[data-test-id=name] [type=text]").setValue(DataGenerator.ClientInfo.generateByClient.fullName); // ввод сгенерированного имени;
        $("[data-test-id=phone] [type=tel]").setValue(DataGenerator.ClientInfo.phoneNumber); // ввод сгенерированного номера телефона;
        $("[data-test-id=agreement]").click(); // клик по чб;
        $("[type=button] [class='button__text']").click(); // клик по копке "Отправить";
        $("[data-test-id=success-notification]").shouldBe(Condition.visible); // Проверка нтификейшена (ДОРАБОТАТЬ: сделать проверку текста и даты);
        $("[data-test-id=date] [class='input__box'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE); // очистка инпута даты;
        $("[data-test-id=date] [type=tel]").setValue(DataGenerator.DateGenerator.newMeetingDate.newFormatter); // ввод даты в нужном формате в инпут;
        String successText = $("[data-test-id=success-notification]").getText(); // получить текст и дату из нотификейшена;
        assertEquals("Успешно!\nВстреча успешно запланирована на " + newMeetingDate.format(newFormatter), successText); // сравнить текст и дату из нотификейшена с ожидаемым текстом и текущей датой;
    }
}