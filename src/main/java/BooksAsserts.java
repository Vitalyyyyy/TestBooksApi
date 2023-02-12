import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class BooksAsserts {
//проверка созданния книги
    public void checkAddBookPositive(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(201).body("book.id", is(notNullValue()));
    }
//Проверка возврата списка книг
    public void checkBodyListBooks(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(200).body("books", notNullValue());
    }
//Проверка отсутствия книги
    public void checkAddBookNegative(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(404).body("book.id", nullValue());
    }
//Проверка изменения книги
    public void checkStatusAndBodyBookAtId(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(200).body("book.id", is(notNullValue()));
    }
    //Негативная возрата 400 + error_message при изменении
    public void checkUpdateBookNegativeWithoutName(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(400).body("error", equalTo("Author is required"));
    }
    //Негативная возрата 400 + error_message при изменении
    public void checkAddBookWhithoutNameF(ValidatableResponse response) {
        response.assertThat().log().all().statusCode(400).body("error", equalTo("Name is required"));
    }


}
