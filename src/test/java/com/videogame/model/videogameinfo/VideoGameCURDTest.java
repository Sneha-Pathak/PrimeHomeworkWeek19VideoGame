package com.videogame.model.videogameinfo;

import com.videogame.model.constants.EndPoints;
import com.videogame.model.model.VideoGamePojo;
import com.videogame.model.testbase.TestBase;
import com.videogame.model.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

//@RunWith(SerenityRunner.class)
public class VideoGameCURDTest extends TestBase
{
    static int id = 12 + TestUtils.getRandomValueInInt();
    static String name = "Mario" + TestUtils.getRandomValue();
    static String releaseDate = "2020-07-20";
    static int reviewScore = 7;
    static String category = "Platform";
    static String rating = "Universal";

    @Title("This will create a new video game")
    @Test
    public void test001()
    {
        VideoGamePojo videoGamePojo = new VideoGamePojo();
        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releaseDate);
        videoGamePojo.setReviewScore(reviewScore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);

        SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(videoGamePojo)  //you can pass body here as well
                .when()
                .post()
                .then().log().all().statusCode(200);
    }

    @Title("Verify if the video game was added to the application")
    @Test
    public void test002()
    {
        String p1 = "findAll{it.name=='";
        String p2 = "'}.post(0)";

        HashMap<String, Object> value =
                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get(EndPoints.GET_ALL_VIDEOGAME)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2);

        assertThat(value, hasValue(name));
        System.out.println(value);
    }

    @Title("Update the category information and verify the updated information")
    @Test
    public void test003()
    {
        String p1 = "findAll{it.id=='";
        String p2 = "'}.get(0)";

        name = name + "_Updated";
        VideoGamePojo videoGamePojo = new VideoGamePojo();
        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releaseDate);
        videoGamePojo.setReviewScore(reviewScore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);
        SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .body(videoGamePojo)  //you can pass body here as well
                .when()
                .put(EndPoints.UPDATE_VIDEOGAME_BY_ID)
                .then().log().all().statusCode(200);

        HashMap<String, Object> value =
                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get(EndPoints.GET_ALL_VIDEOGAME)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + id + p2);

        assertThat(value, hasValue(id));
        System.out.println(value);
    }


    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004()
    {
        SerenityRest.rest()
                .given()
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_VIDEOGAME_BY_ID)
                .then()
                .statusCode(200);

        SerenityRest.rest()
                .given()
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_SINGLE_VIDEOGAME_BY_ID)
                .then()
                .statusCode(200);
    }
}
