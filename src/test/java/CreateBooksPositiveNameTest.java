import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;



@RunWith(Parameterized.class)
public class CreateBooksPositiveNameTest {


    private StepBooks step;
    private BooksAsserts check;

    private String name;
    private String author;
    private int year;
    private boolean isElectronicBook;

    String id;

    public CreateBooksPositiveNameTest(String name, String author, int year, boolean isElectronicBook) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.isElectronicBook = isElectronicBook;

    }

    @Before
    public void preconditions() {
        step = new StepBooks();
        check = new BooksAsserts();
    }

    @Parameterized.Parameters
    public static Object[][] CreateBooksTestParam() {
        return new Object[][]{
                {"java", "Джава", 1993, true},
                {"Джава", "java" , 1993, false},
                {"Джава для чайников", "ja va", 1993, false}
        };
    }
    @Test
    public void createTest() {
        Books orderColor = new Books(name, author,year, isElectronicBook);
        ValidatableResponse response = step.postBook(orderColor);
        check.checkAddBookPositive(response);
        id = response.extract().path("book.id").toString();

    }
    @After
    public void cleanUP() {
        if (id == null) {
            System.out.println("Негативные запросы не прошли валидацию, нужно почистить");
        }
        step.deleteBook(id);
    }
}