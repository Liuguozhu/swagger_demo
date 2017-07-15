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
@Api(value = "/swaggerTest", description = "gets some data from a servlet", tags = "你好啊")
public class SwaggerController2 extends Controller {

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

    @ApiOperation(httpMethod = "GET", value = "追加接口", response = Json.class, nickname = "作者名称2")
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
    public void test5() {
//        renderText("Hello");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("key1", "vaule1");
        result.put("key2", "vaule2");
        renderJson(result);
    }

    @ApiOperation(httpMethod = "GET", value = "获取测试消息接口", response = Json.class, nickname = "LGZ")
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
    public void test4() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("key1", "vaule1");
        result.put("key2", "vaule2");
        renderJson(result);
    }
}