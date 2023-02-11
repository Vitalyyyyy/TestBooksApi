import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class BooksAsserts {

    public void checkAddBookPositive(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(201).body("book.id", is(notNullValue()));
    }

    public void checkBodyListBooks(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(200).body("books", notNullValue());
    }

    public void checkAddBookNegative(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(404).body("book.id", nullValue());
    }

    public void checkStatusAndBodyBookAtId(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(200).body("book.id", is(notNullValue()));
    }
    public void checkUpdateBookNegative(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(400).body("error", equalTo("Author is required"));
    }
    public void checkAddBookWhithoutNameF(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(400).body("error", equalTo("Name is required"));
    }


}
