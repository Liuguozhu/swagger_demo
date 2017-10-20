# swagger_demo

## This project is just a demo combination of jfinal framework and Swagger framework.

There's a lot of stuff that doesn't work because it's copied from the old project.You can clean it by yourself.

Requires more than JDK1.6

resources for the configuration file directory, database account password are the encrypted string, encryption rules see the src/org.sidao.jdbc.CipherUtils.java

There is no need to configure the database,annotate the database in the src/org.sidao.jfinal.JfinalConfig.java and comment out or delete the relevant code ( The default is commented out and can be started directly ) .

The tools directory is an automatic generation of beans and controlles tools and templates for self-written jfinal frameworks.You can change according to actual requirements.

You can start the project through test/RunProject.java,
Direct main method starts.Startup parameters can be modified by themselves.See the jfinal source code or the official document for the parameter significance.

After start-up visit http://localhost:8080/swagger/index.html to view sample to swagger.

The related path configuration for swagger is shown in web.xml.

The sample code for the swagger interface is shown in the SRC/swagger directory.

For other USES of the swagger annotation, see https://swagger.io/ or other documents.


## jfinal框架和swagger的结合demo

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


