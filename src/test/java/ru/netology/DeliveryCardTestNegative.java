package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTestNegative {
    public String choiceDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void FieldCityUncorrectBecauseOfLatin() {
        $("[data-test-id=city] input").setValue("Bromwich");
        String planningDay = choiceDate(3, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDay);
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=phone] input").setValue("+79602546732");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void PhoneFieldTooLong() {
        $("[data-test-id=city] input").setValue("Астрахань");
        String planningDay = choiceDate(3, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDay);
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=phone] input").setValue("+7960254673222");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    public void FieldCityUncorrectBecauseOfDigits() {
        $("[data-test-id=city] input").setValue("Астр0ахань16");
        String planningDay = choiceDate(3, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDay);
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=phone] input").setValue("+7960254673222");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }
    @Test
    public void FieldNameUncorrectBecauseOfDigits() {
        $("[data-test-id=city] input").setValue("Астрахань");
        String planningDay = choiceDate(3, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDay);
        $("[data-test-id=name] input").setValue("Василий Иванов21");
        $("[data-test-id=phone] input").setValue("+79602546732");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы," +
                        " пробелы и дефисы."));
    }
    @Test
    public void FieldNameNull() {
        $("[data-test-id=city] input").setValue("Астрахань");
        String planningDay = choiceDate(3, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDay);
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79602546732");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void FieldDateNull() {
        $("[data-test-id=city] input").setValue("Москва");
        String planningDay = choiceDate(0, "");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDay);
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=phone] input").setValue("+79602546732");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=date] .input__sub").shouldBe(visible).
               shouldHave(exactText("Неверно введена дата"));
    }
    @Test
    public void FieldPhoneNull() {
        $("[data-test-id=city] input").setValue("Астрахань");
        String planningDay = choiceDate(3, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDay);
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));;
    }
    @Test
    public void FieldCityNull() {
        $("[data-test-id=city] input").setValue("");
        String planningDay = choiceDate(3, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDay);
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=phone] input").setValue("+79602546732");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }
}

