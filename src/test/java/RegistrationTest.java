import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldRegisterForDeliveryCard() {
        open("http://localhost:9999/");
        $("[data-test-id='city']").setValue("Казань");
        $("[data-test-id='date']").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name']").setValue("Жолтаева Сабина");
        $("[data-test-id='phone']").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $("button").click();
        $(withText("Встреча успешно забронирована")).shouldBe(Condition.hidden, Duration.ofSeconds(100));
        $("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(100));
        $("[data-test-id=notification]").shouldHave(Condition.text("Успешно!\n" +
                "Встреча успешно забронирована на " + generateDate(6, "dd.MM.yyyy"))).shouldBe(Condition.visible);
    }
}
