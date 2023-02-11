import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;

public class BooksListTest {

       private StepBooks step = new StepBooks();
        private BooksAsserts check = new BooksAsserts();

        @Test
        public void checkBody() {
            ValidatableResponse response = step.getBooksList();
            check.checkBodyListBooks(response);
        }

        @Test
        public void checkIdDoesNotExistBook() {
            String idRandom = RandomStringUtils.randomNumeric(8);
            ValidatableResponse response2 = step.getBook(idRandom);
            check.checkAddBookNegative(response2);
        }

    }

