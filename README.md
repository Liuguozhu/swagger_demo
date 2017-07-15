#swagger_demo
jfinal框架和swagger的结合demo，
有很多没用的东西是因为从旧项目中拷贝过来没有清理。可自行清理。
环境要求JDK1.6以上。

resources为配置文件目录，数据库的账号密码为加密后的字符串，加密规则及示例见src/org.sidao.jdbc.CipherUtils.java。

无需配置数据库 将src/org.sidao.jfinal.JfinalConfig.java中的数据库配置相关代码注释掉或删除即可（默认已注释掉，可直接启动）。

tools目录为自写的一个自动生成jfinal框架的bean和controlles的工具及模板。可根据实际需求自行更改。

启动类 test/RunProject.java。
直接main方法启动。启动参数可自行修改。参数意义详见jfinal源码或官方文档。

启动后访问http://localhost:8080/swagger/index.html 即可查看到swagger的示例。

swagger的相关路径配置见web.xml。

swagger接口的示例代码见src/swagger目录。

swagger注释的其他用法，详见https://swagger.io/ 或其他文档


