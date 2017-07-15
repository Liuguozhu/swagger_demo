package org.sidao.tool;

import com.jfinal.plugin.activerecord.Db;

public class StasisUtil {
	public static void actualStasis (statisInfo si){
		int res=Db.use("base").update("update ACTUAL_STASIS_INFO set CLECK_TIME=CLECK_TIME+"+si.getClick()+",AROUSE_TIMES=AROUSE_TIMES+"+si.getArouseTimes()+",USER_QUAN=USER_QUAN+"+si.getUser_quan()+",SHOE_TIME=SHOE_TIME+"+si.getShowTimes()+",DOWNLOAD_TIME=DOWNLOAD_TIME+"+si.getDownload()+" where CHANNEL_ID=? and APP_ID=?  and ADV_ID=?  and CDATE=? and OS_TYPE=?",si.getChannelId(),si.getApId(),si.getApId(),si.getDate(),si.getOsType());
		if(res==0){
			Db.use("base").update("INSERT INTO ACTUAL_STASIS_INFO (CHANNEL_ID,APP_ID,ADV_ID,CDATE,OS_TYPE,CLECK_TIME,AROUSE_TIMES,USER_QUAN,SHOE_TIME,DOWNLOAD_TIME) SELECT ?,?,?,?,?,?,?,?,?,? FROM dual WHERE NOT EXISTS(SELECT * FROM ACTUAL_STASIS_INFO WHERE CHANNEL_ID=? and APP_ID=? and ADV_ID=? and CDATE=? and OS_TYPE=?)", new Object[]{si.getChannelId(),si.getApId(),si.getApId(),si.getDate(),si.getOsType(),0,0,0,0,0,si.getChannelId(),si.getApId(),si.getAdId(),si.getDate(),si.getOsType()});
			Db.use("base").update("update ACTUAL_STASIS_INFO set CLECK_TIME=CLECK_TIME+"+si.getClick()+",AROUSE_TIMES=AROUSE_TIMES+"+si.getArouseTimes()+",USER_QUAN=USER_QUAN+"+si.getUser_quan()+",SHOE_TIME=SHOE_TIME+"+si.getShowTimes()+",DOWNLOAD_TIME=DOWNLOAD_TIME+"+si.getDownload()+" where CHANNEL_ID=? and APP_ID=?  and ADV_ID=?  and CDATE=? and OS_TYPE=?",si.getChannelId(),si.getApId(),si.getAdId(),si.getDate(),si.getOsType());
		}	
	}
}
