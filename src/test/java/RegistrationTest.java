import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest {

    @Test
    void shouldRegisterForDeliveryCard() {
        open("http://0.0.0.0:9999/");
    }
}
