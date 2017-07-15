package swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

/**
 * Created by LGZ on 2016/8/12.
 */
//@ApiModel(value="用户类", description="用户的相关参数")
public class user {
    @ApiModelProperty(value = "用户名", allowableValues = "字符串1，字符串2")
    public String userName;
    public String nickName;
    @ApiModelProperty(value = "年龄", allowableValues = "1,2")
    public int age;
}
