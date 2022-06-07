package in.reqres.userinfo;

import in.reqres.constants.EndPoints;
import in.reqres.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserSteps {
    @Step("creating user : by name: {0} ,job :{1} ")
    public ValidatableResponse createUser(String name ,String job){
        UserPojo userPojo= new UserPojo();
        userPojo.setName(name);
        userPojo.setJob(job);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post(EndPoints.CREAT_USER)
                .then();

    }
    @Step("updating user : by id {2}")
    public ValidatableResponse updateUser(String name ,String job,String id){
        UserPojo userPojo= new UserPojo();
        userPojo.setName(name);
        userPojo.setJob(job);

        return  SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("userID",id)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }
    @Step("deleting user : by id {3}")
    public ValidatableResponse deleteUser(String id){
        return SerenityRest.given().log().all()
                .pathParam("userID",id)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
    @Step("get single  : by id {3}")
    public ValidatableResponse getSingleUser(String id){
        return SerenityRest.given().log().all()
                .pathParam("userID",id)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }
}