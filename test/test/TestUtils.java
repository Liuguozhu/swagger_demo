/**
 * 
 */
package test;


import org.sidao.jdbc.BonecpPlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;

/**
 * @author wxc
 *
 */
public class TestUtils {
	public static void initDb(){
		BonecpPlugin bp=new BonecpPlugin();  
		bp.start();
		ActiveRecordPlugin arp = new ActiveRecordPlugin(bp);
		arp.setDialect(new MysqlDialect());
		arp.setShowSql(true);
		

       
		arp.start();
	}
}
