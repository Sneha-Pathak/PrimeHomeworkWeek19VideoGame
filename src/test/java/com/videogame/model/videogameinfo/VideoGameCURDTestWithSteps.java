package com.videogame.model.videogameinfo;

import com.videogame.model.constants.EndPoints;
import com.videogame.model.model.VideoGamePojo;
import com.videogame.model.testbase.TestBase;
import com.videogame.model.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class VideoGameCURDTestWithSteps extends TestBase
{
    static int id = 12 + TestUtils.getRandomValueInInt();
    static String name = "Mario" + TestUtils.getRandomValue();
    static String releaseDate = "2020-07-20";
    static int reviewScore = 7;
    static String category = "Platform";
    static String rating = "Universal";

    @Steps
    VideoGameSteps videoGameSteps;

    @Title("This will create a new video game")
    @Test
    public void test001()
    {
        HashMap<String, Object> value =
        videoGameSteps.createVideoGame(id,name,releaseDate,reviewScore,category,rating)
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath()
                .get();

//        assertThat(value, hasValue(name));
//        System.out.println(value);
    }

    @Title("Verify if the video game was added to the application")
    @Test
    public void test002()
    {
        name = name + "_Updated";
        releaseDate = "2020-02-23 Updated";

        HashMap<String, Object> value =
                 videoGameSteps.updateVideoGame(id,name,releaseDate,reviewScore,category,rating)
                    .statusCode(200)
                         .extract()
                         .response()
                         .body()
                         .jsonPath()
                         .get();

        assertThat(value, hasValue(name));
        System.out.println(value);
    }
}
