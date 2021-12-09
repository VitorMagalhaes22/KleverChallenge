package br.com.klever.openweathermap.testes;

import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.*;

import org.junit.Test;


import static  io.restassured.RestAssured.get;
import static io.restassured.RestAssured.*;

public class Climas {

    //http://api.openweathermap.org/data/2.5/weather?q=Bahia,BR&appid=c7b1a2feeb307d6a8aa93c7ea4c4cbe7

    //https://api.openweathermap.org/data/2.5/weather?q=Bahia,BR

    @Test
    public void obterClimaByName(){
        given()
                .log().all()
                .queryParam("q", "Bahia,BR")
                .queryParam("appid", "c7b1a2feeb307d6a8aa93c7ea4c4cbe7")
                .when()
                    .get("http://api.openweathermap.org/data/2.5/weather")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("name", is("Estado de Bahia"))
                    .body("main.temp", greaterThan(25f) );

    }
    @Test
    public void erroAcessarClimaSemToken(){
        given()
                .log().all()
                .queryParam("q", "Bahia,BR")
                .when()
                    .get("http://api.openweathermap.org/data/2.5/weather")
                .then()
                     .log().all()
                     .statusCode(401)
                     .body("message", is("Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."));
    }

    @Test
    public void obterClimaById(){
        given()
                .log().all()
                .queryParam("id", "2172797")
                .queryParam("appid", "c7b1a2feeb307d6a8aa93c7ea4c4cbe7")
                .when()
                .get("http://api.openweathermap.org/data/2.5/weather")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Cairns"))
                .body("main.temp", greaterThan(25f) );

    }
    @Test
    public void obterClimaByCoodernadas(){
        given()
                .log().all()
                .queryParam("lat", "35")
                .queryParam("lon", "139")
                .queryParam("appid", "c7b1a2feeb307d6a8aa93c7ea4c4cbe7")
                .when()
                .get("http://api.openweathermap.org/data/2.5/weather")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Shuzenji"))
                .body("id", is(1851632))
                .body("main.temp", greaterThan(26f) );

    }

    @Test
    public void obterClimaByZIp(){
        given()
                .log().all()
                .queryParam("zip", "94040,us")
                .queryParam("appid", "c7b1a2feeb307d6a8aa93c7ea4c4cbe7")
                .when()
                .get("http://api.openweathermap.org/data/2.5/weather")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Mountain View"))
                .body("main.temp", greaterThan(27f) );


    }
    @Test
    public void erroBuscarZipInexistente(){
        given()
                .log().all()
                .queryParam("zip", "0234322,br")
                .queryParam("appid", "c7b1a2feeb307d6a8aa93c7ea4c4cbe7")
                .when()
                .get("http://api.openweathermap.org/data/2.5/weather")
                .then()
                .log().all()
                .statusCode(404)
                .body("message", is("city not found"));
    }


}
