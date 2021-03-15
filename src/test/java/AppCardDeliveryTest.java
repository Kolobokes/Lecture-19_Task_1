import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

    public String gatDate(int shift) {
        LocalDate date = LocalDate.now();
        LocalDate plusDate = date.plusDays(shift);

        String dateString = plusDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return dateString;
    }

    @Test
    void correctTest(){

        String correctDate = gatDate(4);

        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] .input__control").setValue("Москва");
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] .input__control").setValue(correctDate);
        form.$("[data-test-id='name'] .input__control").setValue("Иванов Иван Иванович");
        form.$("[data-test-id='phone'] .input__control").setValue("+79054040005");
        form.$(".checkbox__box").click();
        form.$(withText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void incorrectCityTest(){

        String correctDate = gatDate(4);

        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] .input__control").setValue("Горячий ключ");
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] .input__control").setValue(correctDate);
        form.$("[data-test-id='name'] .input__control").setValue("Иванов Иван Иванович");
        form.$("[data-test-id='phone'] .input__control").setValue("+79054040005");
        form.$(".checkbox__box").click();
        form.$(withText("Забронировать")).click();
        $("[data-test-id='notification']").shouldNotBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void incorrectDateTest(){

        String incorrectDate = gatDate(0);

        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] .input__control").setValue("Горячий ключ");
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] .input__control").setValue(incorrectDate);
        form.$("[data-test-id='name'] .input__control").setValue("Иванов Иван Иванович");
        form.$("[data-test-id='phone'] .input__control").setValue("+79054040005");
        form.$(".checkbox__box").click();
        form.$(withText("Забронировать")).click();
        $("[data-test-id='notification']").shouldNotBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void incorrectNameTest(){

        String correctDate = gatDate(4);

        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] .input__control").setValue("Горячий ключ");
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] .input__control").setValue(correctDate);
        form.$("[data-test-id='name'] .input__control").setValue("Ivanov Ivan Ivanovich 4");
        form.$("[data-test-id='phone'] .input__control").setValue("+79054040005");
        form.$(".checkbox__box").click();
        form.$(withText("Забронировать")).click();
        $("[data-test-id='notification']").shouldNotBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void incorrectPhoneTest(){

        String correctDate = gatDate(4);

        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] .input__control").setValue("Горячий ключ");
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] .input__control").setValue(correctDate);
        form.$("[data-test-id='name'] .input__control").setValue("Иванов Иван Иванович");
        form.$("[data-test-id='phone'] .input__control").setValue("89054040005");
        form.$(".checkbox__box").click();
        form.$(withText("Забронировать")).click();
        $("[data-test-id='notification']").shouldNotBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void NotAgreementTest(){

        String correctDate = gatDate(4);

        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] .input__control").setValue("Горячий ключ");
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] .input__control").setValue(correctDate);
        form.$("[data-test-id='name'] .input__control").setValue("Иванов Иван Иванович");
        form.$("[data-test-id='phone'] .input__control").setValue("89054040005");
        form.$(withText("Забронировать")).click();
        $("[data-test-id='notification']").shouldNotBe(visible, Duration.ofSeconds(15));
    }
}
