package swagger.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import io.swagger.annotations.*;
import io.swagger.util.Json;
import swagger.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 * Created by LGZ on 2016/7/27.
 */
@SwaggerDefinition(
//        host = "192.168.1.152",
        info = @Info(
                description = "描述:swagger sample server 中文 "
                , version = "1.0.1"
                , title = "标题：Swagger API sample"
//                , termsOfService = "服务条款"
//                , contact = @Contact(name = "Sponge-Bob", email = "apiteam@swagger.io", url = "http://swagger.io")
//                , license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
        )
//        ,  consumes = {"application/json", "application/xml"}
//        ,produces = {"application/json", "application/xml"}
//        ,schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS}
        , basePath = "/"
//        ,tags = {@Tag(name = "users", description = "Operations about user")}
)
@Api(value = "/swaggerTest", description = "gets some data from a servlet", tags = "登陆接口")
public class SwaggerController extends Controller {

    @Clear
    public void index() {
        render("/swagger/index.html");
    }


    @ApiOperation(httpMethod = "POST", value = "获取测试消息接口", response = String.class, nickname = "LGZ")
    @ApiResponses({
            @ApiResponse(code = 500, message = "服务器异常", response = String.class)
            , @ApiResponse(code = 200, message = "Invalid input", response = String.class, reference = "user")
            , @ApiResponse(code = 404, message = "找不到页面", response = String.class)
    })

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User ID", required = true, dataType = "integer", paramType =
                    "query"),
            @ApiImplicitParam(name = "name", value = "User's name", required = true, dataType = "string", paramType =
                    "query"),
            @ApiImplicitParam(name = "dateOfBirth", value = "User's date of birth, in dd-MM-yyyy format", required =
                    true, dataType = "date", paramType = "query")
    })
    @Clear
    public void test() {
        String id = getPara("id");
        System.out.println("id=" + id);
        String name = getPara("name");
        System.out.println("name=" + name);
        Date dateOfBirth = getParaToDate("dateOfBirth");
        System.out.println("dateOfBirth=" + dateOfBirth);
        renderText("Hello");
    }

    @ApiOperation(httpMethod = "GET", value = "获取测试消息接口2", response = Json.class, nickname = "作者名称2")
//    @ApiResponses({@ApiResponse(code = 200, message = "Invalid input", response = Json.class)})
    @ApiResponses({
            @ApiResponse(code = 500, message = "服务器异常", response = Json.class)
            , @ApiResponse(code = 200, message = "Invalid input", response = user.class)
            , @ApiResponse(code = 404, message = "找不到页面", response = Json.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User ID", required = true, dataType = "integer", paramType =
                    "query"),
            @ApiImplicitParam(name = "name", value = "User's name", required = true, dataType = "string", paramType =
                    "query"),
            @ApiImplicitParam(name = "dateOfBirth", value = "User's date of birth, in dd-MM-yyyy format", required =
                    true, dataType = "java.util.Date", paramType = "query")})
    @Clear
    public void test2() {
//        renderText("Hello");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("key1", "vaule1");
        result.put("key2", "vaule2");
        renderJson(result);
    }

    @ApiOperation(httpMethod = "GET", value = "获取测试消息接口3", response = Json.class, nickname = "LGZ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "登陆成功", response = Json.class, reference = "{'a':'姓名','b':'你好'}")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User ID", required = false, dataType = "integer", paramType =
                    "query"),
            @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType =
                    "query"),
            @ApiImplicitParam(name = "dateOfBirth", value = "User's date of birth, in dd-MM-yyyy format", required =
                    false, dataType = "java.util.Date", paramType = "query")})
    @Clear
    public void test3() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("key1", "vaule1");
        result.put("key2", "vaule2");
        renderJson(result);
    }
}