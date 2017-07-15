/**
 * 
 */
package org.sidao.tool.jdbc;

/**
 * @author wxc
 * 
 * 
 */
public final class SystemTables {
	/**
	 * 
	 */
	public final static String FS_MENU_NAME = "FS_MENU";
	/**
	 * 
	 */
	public final static class FS_MENU{
		/**
		 * 菜单主键
		 */
		public final static String MENU_ID = "MENU_ID";
		/**
		 * 父菜单主键
		 */
		public final static String PARENT_ID = "PARENT_ID";
		/**
		 * 命名空间
		 */
		public final static String NAMESPACE = "NAMESPACE";
		/**
		 * 菜单名称
		 */
		public final static String MENU_NAME = "MENU_NAME";
		/**
		 * 顺序
		 */
		public final static String MENU_SEQ = "MENU_SEQ";
		/**
		 * 样式
		 */
		public final static String MENU_STYLE = "MENU_STYLE";
	}
	/**
	 * 
	 */
	public final static String FS_OPERATION_NAME = "FS_OPERATION";
	/**
	 * 
	 */
	public final static class FS_OPERATION{
		/**
		 * 命名空间
		 */
		public final static String NAMESPACE = "NAMESPACE";
		/**
		 * 操作
		 */
		public final static String OPERATION = "OPERATION";
		/**
		 * 法方，如果此值不为空，则METHOD会覆盖OPERATION
		 */
		public final static String METHOD = "METHOD";
		/**
		 * 述描
		 */
		public final static String DESCRIPTION = "DESCRIPTION";
		/**
		 * SQL表达式
		 */
		public final static String SQL_EXPR = "SQL_EXPR";
		/**
		 * 表名
		 */
		public final static String TABLE_NAME = "TABLE_NAME";
		/**
		 * 前置类
		 */
		public final static String BEFORE_CLASS = "BEFORE_CLASS";
		/**
		 * 处理类
		 */
		public final static String SERVICE_CLASS = "SERVICE_CLASS";
		/**
		 * 置后类
		 */
		public final static String AFTER_CLASS = "AFTER_CLASS";
		/**
		 * 全局
		 */
		public final static String GLOBABLE = "GLOBABLE";
		/**
		 * 主操作
		 */
		public final static String MAINABLE = "MAINABLE";
		/**
		 * 事物
		 */
		public final static String TRANSACTION = "TRANSACTION";
	}
	/**
	 * 
	 */
	public final static String FS_ORG_NAME = "FS_ORG";
	/**
	 * 
	 */
	public final static class FS_ORG{
		/**
		 * 键主
		 */
		public final static String ORG_ID = "ORG_ID";
		/**
		 * 上级组织
		 */
		public final static String PARENT_OID = "PARENT_OID";
		/**
		 * 组织编号
		 */
		public final static String ORG_NO = "ORG_NO";
		/**
		 * 织组名称
		 */
		public final static String ORG_NAME = "ORG_NAME";
		/**
		 * 组织类型
		 */
		public final static String ORG_TYPE = "ORG_TYPE";
		/**
		 * 是否可用
		 */
		public final static String ENABLE = "ENABLE";
		/**
		 * 备注
		 */
		public final static String MEMO = "MEMO";
	}
	/**
	 * 
	 */
	public final static String FS_ROLE_NAME = "FS_ROLE";
	/**
	 * 
	 */
	public final static class FS_ROLE{
		/**
		 * id
		 */
		public final static String ROLE_ID = "ROLE_ID";
		/**
		 * 角色名称
		 */
		public final static String ROLE_NAME = "ROLE_NAME";
		/**
		 * 作操人
		 */
		public final static String OPERATOR = "OPERATOR";
		/**
		 * 作操时间
		 */
		public final static String OPERATE_DATE = "OPERATE_DATE";
		/**
		 * 注备
		 */
		public final static String MEMO = "MEMO";
	}
	/**
	 * 
	 */
	public final static String FS_ROLE_OPERATION_NAME = "FS_ROLE_OPERATION";
	/**
	 * 
	 */
	public final static class FS_ROLE_OPERATION{
		/**
		 * 
		 */
		public final static String NAMESPACE = "NAMESPACE";
		/**
		 * 
		 */
		public final static String OPERATION = "OPERATION";
		/**
		 * 
		 */
		public final static String ROLE_ID = "ROLE_ID";
		/**
		 * 
		 */
		public final static String OPERATOR = "OPERATOR";
		/**
		 * 
		 */
		public final static String OPERATE_DATE = "OPERATE_DATE";
	}
	/**
	 * 
	 */
	public final static String FS_ROLE_USER_NAME = "FS_ROLE_USER";
	/**
	 * 
	 */
	public final static class FS_ROLE_USER{
		/**
		 * 
		 */
		public final static String ROLE_ID = "ROLE_ID";
		/**
		 * 
		 */
		public final static String USER_ID = "USER_ID";
		/**
		 * 
		 */
		public final static String OPERATOR = "OPERATOR";
		/**
		 * 
		 */
		public final static String OPERATE_DATE = "OPERATE_DATE";
	}
	/**
	 * 
	 */
	public final static String FS_SERVICE_NAME = "FS_SERVICE";
	/**
	 * 
	 */
	public final static class FS_SERVICE{
		/**
		 * 命名空间
		 */
		public final static String NAMESPACE = "NAMESPACE";
		/**
		 * 服务名
		 */
		public final static String SERVICE_NAME = "SERVICE_NAME";
		/**
		 * 连接器
		 */
		public final static String CONNECTOR_NAME = "CONNECTOR_NAME";
		/**
		 * URL地址
		 */
		public final static String URL = "URL";
		/**
		 * 作者
		 */
		public final static String AUTHOR = "AUTHOR";
		/**
		 * 是否可用
		 */
		public final static String ENABLE = "ENABLE";
		/**
		 * 描述
		 */
		public final static String DESCRIPTION = "DESCRIPTION";
		/**
		 * 权授,1:与授权有关；0：全局模块；2：管理员模块'
		 */
		public final static String GRANTABLE = "GRANTABLE";
	}
	/**
	 * 
	 */
	public final static String FS_USER_NAME = "FS_USER";
	/**
	 * 
	 */
	public final static class FS_USER{
		/**
		 * ID
		 */
		public final static String USER_ID = "USER_ID";
		/**
		 * 帐号
		 */
		public final static String USER_CODE = "USER_CODE";
		/**
		 * 拼音码
		 */
		public final static String PYM = "PYM";
		/**
		 * 用户名称@search
		 */
		public final static String USER_NAME = "USER_NAME";
		/**
		 * ORG_ID部门参考主键
		 */
		public final static String ORG_ID = "ORG_ID";
		/**
		 * 密码
		 */
		public final static String PASSWORD = "PASSWORD";
		/**
		 * EMAIL
		 */
		public final static String EMAIL = "EMAIL";
		/**
		 * 用户类型
		 */
		public final static String USER_TYPE = "USER_TYPE";
		/**
		 * 是否启用@combo
		 */
		public final static String ENABLE = "ENABLE";
		/**
		 * 住址
		 */
		public final static String ADDRESS = "ADDRESS";
		/**
		 * 移动电话
		 */
		public final static String MOBILE = "MOBILE";
		/**
		 * QQ号
		 */
		public final static String QQ = "QQ";
		/**
		 * 性别
		 */
		public final static String SEX = "SEX";
		/**
		 * 职位
		 */
		public final static String POST = "POST";
		/**
		 * 备注
		 */
		public final static String COMMENT = "COMMENT";
	}
	/**
	 * 
	 */
	public final static String FS_USER_OPERATION_NAME = "FS_USER_OPERATION";
	/**
	 * 
	 */
	public final static class FS_USER_OPERATION{
		/**
		 * 
		 */
		public final static String NAMESPACE = "NAMESPACE";
		/**
		 * 
		 */
		public final static String OPERATION = "OPERATION";
		/**
		 * 
		 */
		public final static String USER_ID = "USER_ID";
		/**
		 * 
		 */
		public final static String OPERATOR = "OPERATOR";
		/**
		 * 
		 */
		public final static String OPERATE_DATE = "OPERATE_DATE";
	}
}