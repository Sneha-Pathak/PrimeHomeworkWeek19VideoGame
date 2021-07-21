package com.videogame.model.testbase;

import com.videogame.model.constants.Path;
import com.videogame.model.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase
{
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init(){
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
        RestAssured.basePath = Path.VIDEOGAME;
    }

}
