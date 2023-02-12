import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


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
        books2 = BooksGenerator.getBookfullField();
    }

    @After
    public void cleanUP() {
        step.deleteBook(id);
    }

    //Добавления книги только с name
    @Test
    public void addBookOnlyNameField() {
        ValidatableResponse response = step.postBook(books);
        id = response.extract().path("book.id").toString();
        check.checkAddBookPositive(response);

    }

    //Получение добавленной книгм по id
    @Test
    public void checkIdBook() {
        ValidatableResponse response = step.postBook(books);
        id = response.extract().path("book.id").toString();
        check.checkAddBookPositive(response);
        ValidatableResponse response2 = step.getBook(id);
        check.checkStatusAndBodyBookAtId(response2); //можно реализовать проверку на конкретный id непосредственно в методе
    }

    //Изменение созданной книги
    @Test
    public void updateBookPositiveTest() {
        ValidatableResponse response = step.postBook(books2);
        id = response.extract().path("book.id").toString();
        ValidatableResponse response2 = step.putBook(id);
        check.checkStatusAndBodyBookAtId(response2);
    }

}
