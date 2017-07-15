package org.sidao.cm.common;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.sidao.common.VerificationCode;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;


public class CommonController extends Controller {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
            .getLogger(CommonController.class);

    /**
     * Index void.
     */
    @Clear
    public void index() {
        render("common/login.html");
    }

    public void main() {
        setAttr("cuser", getSessionAttr("cuser"));

        render("/common/main.html");
    }

    public void mainindex() {
        render("/common/mainindex.html");
    }

    /**
     * 验证码
     */
    @Clear
    public void random_code() {
        VerificationCode img = new VerificationCode("cmSystemRandomCode");
        render(img);
    }

}
