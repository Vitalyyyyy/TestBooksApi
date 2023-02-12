import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;


public class StepBooks extends BooksConstant {


    private static final String BOOKS = "/api/books";

    private static final String BOOKS_ID = "/api/books/{id}";




    @Step("add new book")
    public ValidatableResponse postBook(Books books) {
        return given()
                .spec(getSpec())
                .body(books)
                .when()
                .post(BOOKS)
                .then();
    }

    @Step("Get book")
    public ValidatableResponse getBook(String id) {
        return given()
                .spec(getSpec())
                .when().log().all()
                .get(BOOKS_ID, id)
                .then();
    }

    @Step("Get books list")
    public ValidatableResponse getBooksList() {
        return given()
                .spec(getSpec())
                .when().log().all()
                .get(BOOKS)
                .then();
    }

    @Step("Update book")
    public ValidatableResponse putBook(String id) {
        return given()
                .spec(getSpec())
                .body(BooksGenerator.getBookfullField())
                .when()
                .put(BOOKS_ID, id)
                .then();

    }
    @Step("Update book epmty name and nothing")
    public ValidatableResponse putNegativeBookOnlyEmptyName(String id) {
        return given()
                .spec(getSpec())
                .body(BooksGenerator.getBookEmptyName())
                .when()
                .put(BOOKS_ID, id)
                .then();

    }

    @Step("Delete book")
    public ValidatableResponse deleteBook(String id) {
        return given()
                .spec(getSpec())
                .when()
                .delete(BOOKS_ID, id)
                .then();

    }
}




