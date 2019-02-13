package com.yajie.springboot.learn.common.util;

/** 
* @ClassName: Const
* @Description: 常量定义类
* @author: WangYiZhi 
* @email: yizhi_wang@126.com
* @date: 2017年10月26日 上午11:28:50 
* @version V1.0  
*/ 
public class Const {
	
	public static final String SYSNAME = "config/SYSNAME.txt";	//系统名称路径
	public static final String SESSION_SECURITY_CODE = "sessionSecCode"; //验证码
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//当前菜单
	public static final String SESSION_btnIds = "btnIds";			//当前按钮id集合
	public static final String SESSION_allmenuList = "allmenuList";		//全部菜单
	public static final String SESSION_QX = "QX";	//系统权限
	public static final String SESSION_userpds = "userpds";			
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME"; // 用户名
	public static final String FILEPATHIMG = "/uploadFiles/uploadImgs/"; // 图片上传路径
	public static final String FILEPATHCERTIMG = "/uploadFiles/uploadImgs/certImgs/"; // 资质证书图片上传路径
	public static final String FILEPATHADVERTIMG = "/uploadFiles/uploadImgs/advertImgs/"; // 广告图片上传路径
	public static final String FILEPATHBANNERIMG = "/uploadFiles/uploadImgs/bannerImgs/"; // banner图片上传路径
	public static final String FILEPATHNEWIMG = "/uploadFiles/uploadImgs/newImgs/"; // 资讯图片上传路径
	public static final String FILEPATHICONIMG = "/uploadFiles/uploadImgs/iconImgs/"; // 图片上传路径
	public static final String FILEPATHFILE = "/uploadFiles/file/"; // 文件上传路径
	public static final String FWATERM = "config/FWATERM.txt";	//文字水印配置路径
	public static final String IWATERM = "config/IWATERM.txt";	//图片水印配置路径
	public static final String FILEPATHTWODIMENSIONCODE = "/uploadFiles/twoDimensionCode/"; // 二维码存放路径

	/**
	 * APP Constants
	 */
	// app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[] { "countries", "uname", "passwd", "title",
			"full_name", "company_name", "countries_code", "area_code", "telephone", "mobile" };
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[] { "国籍", "邮箱帐号", "密码", "称谓", "名称", "公司名称",
			"国家编号", "区号", "电话", "手机号" };

	// app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[] { "USERNAME" };
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[] { "用户名" };

}
