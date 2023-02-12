import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;



@RunWith(Parameterized.class)
public class CreateBooksNegativeNameTest {


    private StepBooks step;
    private BooksAsserts check;

    private String name;
    private String author;
    private int year;
    private boolean isElectronicBook;




    String id;

    public CreateBooksNegativeNameTest(String name, String author, int year, boolean isElectronicBook) {
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
                {"", "Джава", 1993, true},
                {"  ", "java" , 1993, false},
                {"!%$#","java",2030, false},
        };
    }
    @Test
    public void createTest() {
        Books orderColor = new Books(name, author, year, isElectronicBook);
        ValidatableResponse response = step.postBook(orderColor);
        id = response.extract().path("book.id").toString();
        check.checkAddBookNegative(response);
    }
    @After
    public void cleanUP() {
        if (id == "201") {
            System.out.println("Негативные запросы не прошли валидацию");
        }
        step.deleteBook(id);
    }
}