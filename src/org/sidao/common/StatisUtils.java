package org.sidao.common;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.time.DateUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * @author xcwang
 *
 */
public class StatisUtils {
	
	private static ConcurrentHashMap<String, Integer> appKInfoMap = new ConcurrentHashMap<String, Integer>();
	
	private static ConcurrentHashMap<String, Record> activeUserMap = new ConcurrentHashMap<String, Record>();
	
	public static String SDK_VERSION_V1 = "V1";
	static{
		List<Record> appKInfos = Db.find("select CP_APP_ID, APP_KEY from CP_APP_KEY_INFO");
		for(Record r: appKInfos){
			appKInfoMap.put(r.getStr("APP_KEY"), r.getInt("CP_APP_ID"));
		}
		//缓存激活的账户信息
		List<Record> activeUsers = Db.find("select ISACTIVATE,PHONE,ACCOUNT from gamer_info_extend where ISACTIVATE=0");
		for(Record r: activeUsers){
			activeUserMap.put(r.getStr("ACCOUNT"), r);
		}
	}
	/**
	 * 增加付费金额
	 * @param channelid
	 * @param gameid
	 */
	public static void updateReqFee(Integer typeid,Integer appid,Integer channelid,Integer spid,Integer value,String date){
		Date cdate;
		try{
			cdate = DateUtils.parseDate(date, new String[]{"yyyyMMdd"});
			int res=Db.update("update STATIS set REQ_FEE=?,REQTIME=? where TYPE_ID=? and APP_ID=? and CHANNEL_ID=? and SP_ID=? and CDATE=?",new Object[]{value,new Date(),typeid,appid,channelid,spid,cdate});
			if(res==0){
				Db.update("INSERT INTO statis(TYPE_ID,APP_ID,CHANNEL_ID,SP_ID,CDATE) SELECT ?,?,?,?,? FROM dual WHERE NOT EXISTS(SELECT * FROM statis WHERE TYPE_ID=? and APP_ID=? and CHANNEL_ID=? and SP_ID=? and CDATE=?)", new Object[]{typeid,appid,channelid,spid,cdate,typeid,appid,channelid,spid,cdate});
				Db.update("update STATIS set REQ_FEE=?,REQTIME=? where TYPE_ID=? and APP_ID=? and CHANNEL_ID=? and SP_ID=? and CDATE=?",new Object[]{value,new Date(),typeid,appid,channelid,spid,cdate});
			}
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 增加付费金额
	 * @param channelid
	 * @param gameid
	 */
	public static void updateReqFee(Integer appid,Integer value,String provinceInfo,String date){
		Date cdate;
		try {
			cdate= DateUtils.parseDate(date, new String[]{"yyyyMMdd"});
			int sdp=Db.update("update STATS_BY_PROVINCE set REQUEST_MONEY=? where RDATE=? and GAME_ID=? and PROVINCE=?",value,cdate,appid,provinceInfo);
			if(sdp==0){
				Db.update("INSERT INTO STATS_BY_PROVINCE(PROVINCE,GAME_ID,RDATE) SELECT ?,?,? from dual where NOT EXISTS(SELECT * from STATS_BY_PROVINCE where PROVINCE=? and GAME_ID=? and RDATE=?)",provinceInfo,appid,cdate,provinceInfo,appid,cdate);
				Db.update("update STATS_BY_PROVINCE set REQUEST_MONEY=? where RDATE=? and GAME_ID=? and PROVINCE=?",value,cdate,appid,provinceInfo);
			}
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 增加付费次数
	 */
	public static void updateReqTime(Integer appid,Integer value,String provinceInfo,String date){
		Date cdate;
		try {
			cdate= DateUtils.parseDate(date, new String[]{"yyyyMMdd"});
			int sdp=Db.update("update STATS_BY_PROVINCE set REQUEST_TIME=? where RDATE=? and GAME_ID=? and PROVINCE=?",value,cdate,appid,provinceInfo);
			if(sdp==0){
				Db.update("INSERT INTO STATS_BY_PROVINCE(PROVINCE,GAME_ID,RDATE) SELECT ?,?,? from dual where NOT EXISTS(SELECT * from STATS_BY_PROVINCE where PROVINCE=? and GAME_ID=? and RDATE=?)",provinceInfo,appid,cdate,provinceInfo,appid,cdate);
				Db.update("update STATS_BY_PROVINCE set REQUEST_TIME=? where RDATE=? and GAME_ID=? and PROVINCE=?",value,cdate,appid,provinceInfo);
			}
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 增加成功计费金额
	 * @param channelid
	 * @param gameid
	 */
	public static void updateSuccessFee(Integer typeid,Integer appid,Integer channelid,Integer spid,Integer value,String provinceInfo){
		Date cdate=DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		int res=Db.update("update STATIS set SUCC_FEE=SUCC_FEE+"+value+",SYNCTIME=? where TYPE_ID=? and APP_ID=? and CHANNEL_ID=? and SP_ID=? and CDATE=?",new Object[]{new Date(),typeid,appid,channelid,spid,cdate});
		if(res==0){
			Db.update("INSERT INTO statis(TYPE_ID,APP_ID,CHANNEL_ID,SP_ID,CDATE) SELECT ?,?,?,?,? FROM dual WHERE NOT EXISTS(SELECT * FROM statis WHERE TYPE_ID=? and APP_ID=? and CHANNEL_ID=? and SP_ID=? and CDATE=?)", new Object[]{typeid,appid,channelid,spid,cdate,typeid,appid,channelid,spid,cdate});
			Db.update("update STATIS set SUCC_FEE=SUCC_FEE+"+value+",SYNCTIME=? where TYPE_ID=? and APP_ID=? and CHANNEL_ID=? and SP_ID=? and CDATE=?",new Object[]{new Date(),typeid,appid,channelid,spid,cdate});
		}
		int sdp=Db.update("update STATS_BY_PROVINCE set SUCCESS_MONEY=SUCCESS_MONEY+"+value+",SUCCESS_TIME=SUCCESS_TIME+1 where RDATE=? and GAME_ID=? and PROVINCE=?",cdate,appid,provinceInfo);
		if(sdp==0){
			Db.update("INSERT INTO STATS_BY_PROVINCE(PROVINCE,GAME_ID,RDATE) SELECT ?,?,? from dual where NOT EXISTS(SELECT * from STATS_BY_PROVINCE where PROVINCE=? and GAME_ID=? and RDATE=?)",provinceInfo,appid,cdate,provinceInfo,appid,cdate);
			Db.update("update STATS_BY_PROVINCE set SUCCESS_MONEY=SUCCESS_MONEY+"+value+",SUCCESS_TIME=SUCCESS_TIME+1 where RDATE=? and GAME_ID=? and PROVINCE=?",cdate,appid,provinceInfo);
		}
	}
	
	public static Integer findCPAppIDByAppKey(String appKey){
		Integer CpAppID = appKInfoMap.get(appKey);
		if(CpAppID==null){
			Record appKInfoR = Db.findFirst("select CP_APP_ID from CP_APP_KEY_INFO where APP_KEY=?",new Object[]{appKey});
			if(appKInfoR==null||appKInfoR.getInt("CP_APP_ID")==null){
				return null;
			}
			CpAppID = appKInfoR.getInt("CP_APP_ID");
			appKInfoMap.put(appKey,CpAppID);
		}
		return CpAppID;
		
	}
	
	//查询已激活的用户
	public static Record findUserIsActive(String userId){
		Record r = activeUserMap.get(userId);
		if(r==null){
			r = Db.findFirst("select ISACTIVATE,PHONE,ACCOUNT from gamer_info_extend a where a.ACCOUNT=?", new Object[]{userId});
			if(r!=null&&r.getInt("ISACTIVATE")==0){
				activeUserMap.put(r.getStr("ACCOUNT"), r);
				return r;
			}
		}
		return r;
	}
	

	/**
	 * 增加同步计费金额
	 * @param channelid
	 * @param gameid
	 */
	public static void updateSyncFee(Integer typeid,Integer appid,Integer channelid,Integer spid,Integer value){
		Date cdate=DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		int res=Db.update("update STATIS set SYNC_FEE=SYNC_FEE+"+value+",SYNCTIME=? where TYPE_ID=? and APP_ID=? and CHANNEL_ID=? and SP_ID=? and CDATE=?",new Object[]{new Date(),typeid,appid,channelid,spid,cdate});
		if(res==0){
			Db.update("INSERT INTO statis(TYPE_ID,APP_ID,CHANNEL_ID,SP_ID,CDATE) SELECT ?,?,?,?,? FROM dual WHERE NOT EXISTS(SELECT * FROM statis WHERE TYPE_ID=? and APP_ID=? and CHANNEL_ID=? and SP_ID=? and CDATE=?)", new Object[]{typeid,appid,channelid,spid,cdate,typeid,appid,channelid,spid,cdate});
			Db.update("update STATIS set SYNC_FEE=SYNC_FEE+"+value+",SYNCTIME=? where TYPE_ID=? and APP_ID=? and CHANNEL_ID=? and SP_ID=? and CDATE=?",new Object[]{new Date(),typeid,appid,channelid,spid,cdate});
		}
	} 
	
	/**
	 * 统计刷量！
	 * @param typeid
	 * @param appid
	 * @param channelid
	 * @param spid
	 * @param value
	 */
	public static void updateFlushFee(Integer appid,String province){
		Date cdate=DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		int res=Db.update("update STATIS_FLUSH set COUNT=COUNT+1 where SP_APP_ID=? and CDATE=?",new Object[]{appid,new Date()});
		if(res==0){
			Db.update("INSERT INTO STATIS_FLUSH(SP_APP_ID,COUNT,CDATE) SELECT ?,?,? FROM dual WHERE NOT EXISTS(SELECT * FROM STATIS_FLUSH WHERE SP_APP_ID=? and CDATE=?)", new Object[]{appid,0,cdate,appid,cdate});
			Db.update("update STATIS_FLUSH set COUNT=COUNT+1 where SP_APP_ID=? and CDATE=?",new Object[]{appid,new Date()});
		}
	}
}
