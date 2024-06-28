package user;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LogIn {

    public static final String LOGIN_USER = "/api/auth/login";
    public static final String DELETE_USER = "/api/auth/user";




    @Step("Авторизация пользователя")
    public static Response checkRequestAuthLogin(User user) {
        return given()
                .log()
                .all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(LOGIN_USER);
    }
    @Step ("Удаление пользователя")
    public static Response deleteUser(String accessToken){
        return given()
                .header("Authorization",accessToken)
                .when()
                .delete(DELETE_USER);
    }
}
