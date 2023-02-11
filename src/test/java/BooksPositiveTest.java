import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;

public class BooksPositiveTest {
    private StepBooks step;
    private BooksAsserts check;
    private Books books;
    private Books books2;

    private String id;

    @Before
    public void preconditions() {
        step = new StepBooks();
        check = new BooksAsserts();
        books = BooksGenerator.getBookOnlyName();
        books2 = BooksGenerator.getBookThereIsNotName();
    }

    @After
    public void cleanUP() {
        if (id == null) {
            System.out.println("Негативные запросы не прошли валидацию, нужно проверить базу");
        }
        step.deleteBook(id);
    }

    @Test
    public void addBookOnlyNameField() {
        ValidatableResponse response = step.postBook(books);
        id = response.extract().path("book.id").toString();
        check.checkAddBookPositive(response);

    }

    @Test
    public void checkIdBook() {
        ValidatableResponse response = step.postBook(books);
        id = response.extract().path("book.id").toString();
        check.checkAddBookPositive(response);
        ValidatableResponse response2 = step.getBook(id);
        check.checkStatusAndBodyBookAtId(response2);
    }

    @Test // фулл
    public void updateBookPositiveTest() {
        ValidatableResponse response = step.postBook(books);
        id = response.extract().path("book.id").toString();
        ValidatableResponse response2 = step.putBook(id);
        check.checkStatusAndBodyBookAtId(response2);
    }

}
