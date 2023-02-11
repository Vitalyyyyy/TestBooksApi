import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;

public class BooksNegativeTest {

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

    @Test
    public void addBookWithoutNameField() {
        ValidatableResponse response = step.postBookWithoutNameField(books2);
        check.checkAddBookWhithoutNameF(response);
    }

    @Test
    public void addDublicateBook() {
        ValidatableResponse response = step.postBook(books);
        ValidatableResponse response2 = step.postBook(books);
        id = response.extract().path("book.id").toString();
        id = response2.extract().path("book.id").toString();
        check.checkAddBookNegative(response);
        check.checkAddBookNegative(response2);
    }


    @Test // только пустой name
    public void updateBookOnlyNameField() {
        ValidatableResponse response = step.postBook(books);
        id = response.extract().path("book.id").toString();
        ValidatableResponse response2 = step.putNegativeBook(id);
        check.checkUpdateBookNegative(response2);
    }
}
