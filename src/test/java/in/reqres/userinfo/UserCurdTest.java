package in.reqres.userinfo;

import in.reqres.testbase.TestBase;
import in.reqres.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class UserCurdTest extends TestBase {

    static String name="Rachel"+ TestUtils.getRandomValue();
    static  String job="tester";
    static String userId;
    @Steps
    UserSteps userSteps;
    @Title("Create user ")
    @Test
    public void test001(){
        ValidatableResponse response= userSteps.createUser(name,job);
        response.log().all().statusCode(201);
        userId=response.extract().path("id");
        System.out.println(userId);

    }
    @Title("Get single user  ")
    @Test
    public  void test002(){
        ValidatableResponse response = userSteps.getSingleUser(userId);
        response.log().all().statusCode(200);
        response.body("name",equalTo(name));

    }
    @Title("Update user")
    @Test
    public void test003(){
        name=name+"updated001";
        ValidatableResponse response= userSteps.updateUser(name,job,userId);
        response.log().all().statusCode(200);
        response.body("name",equalTo(name));

    }
    @Title("Delete user ")
    @Test
    public  void test004(){
        ValidatableResponse response = userSteps.deleteUser(userId);
        response.log().all().statusCode(204);

        ValidatableResponse response1 = userSteps.getSingleUser(userId);
        response1.log().all().statusCode(404);

    }


}