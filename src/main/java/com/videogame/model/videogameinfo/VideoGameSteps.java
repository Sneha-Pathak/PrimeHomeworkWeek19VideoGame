package com.videogame.model.videogameinfo;

import com.videogame.model.model.VideoGamePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class VideoGameSteps
{
    @Step("Creating student with id: {0}, name: {1}, releaseDate: {2}, reviewScore: {3} category: {4} and rating: {5}")
    public ValidatableResponse createVideoGame(int id, String name, String releaseDate, int reviewScore, String category, String rating)
    {
        VideoGamePojo videoGamePojo = new VideoGamePojo();
        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releaseDate);
        videoGamePojo.setReviewScore(reviewScore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);

        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .body(videoGamePojo)  //you can pass body here as well
                .when()
                .post()
                .then();
    }

    @Step("Updating student with id: {0}, name: {1}, releaseDate: {2}, reviewScore: {3} category: {4} and rating: {5}")
    public ValidatableResponse updateVideoGame(int id, String name, String releaseDate, int reviewScore, String category, String rating)
    {
        VideoGamePojo videoGamePojo = new VideoGamePojo();
        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releaseDate);
        videoGamePojo.setReviewScore(reviewScore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);

        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .body(videoGamePojo)
                .when()
                .put()
                .then();
    }
}
