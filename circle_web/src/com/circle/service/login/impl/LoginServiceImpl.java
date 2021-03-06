package com.circle.service.login.impl;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.circle.dao.login.ILoginDao;
import com.circle.pojo.user.User;
import com.circle.service.login.ILoginService;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.ProUtil;

/**
 * 
 * 登录业务逻辑层实现类. <br>
 * <p>
 * Copyright: Copyright (c) 2014年12月16日 11:11:37
 * <p>
 * Company: Cooper's Summer
 * <p>
 * @author Cooper
 * @version 1.0.0
 */
@Service
public class LoginServiceImpl implements ILoginService {

	/**
	 * 注入DAO方法
	 */
	@Resource
	private ILoginDao loginDao;
	
	/**
	 * 
	 * 方法描述:修改用户登录时间
	 * @param mpbilePhone 用户手机
	 * date:2014-12-16
	 * @author Cooper
	 */
	public boolean updateUserLastLoginTimer(String mobilePhone) throws SPTException{
		return loginDao.updateUserLastLoginTimer(mobilePhone);
	}
	
	/**
	 * 
	 * 方法描述:根据微信openid修改最后登录时间
	 * @param openid 微信对应订阅号唯一表示
	 * @return 大于0修改成功，否则失败
	 * @throws SPTException
	 * date:2015-6-1
	 * @author wangfengtong
	 */
	public boolean updateUserLastLoginTimerByWeixin(String openid) throws SPTException{
		return loginDao.updateUserLastLoginTimerByWeixin(openid);
	}
	
	/**
	 * 
	 * 方法描述: 用户登录
	 * @param mobilePhone手机号 password密码
	 * @return 返回用户实体类对象
	 * date:2015年1月9日
	 * add by: Cooper
	 * @throws NoSuchAlgorithmException 
	 * @throws SPTException 
	 */
	public User login(String mobilePhone, String password) throws NoSuchAlgorithmException, SPTException{
		User user= loginDao.login(mobilePhone,password);
		if(user!=null){
			user.setImagePath(user.getHeadImage());
			user.setHeadImage(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN)+user.getHeadImage());
		}
		return user;
	}
	
	/**
	 * 
	 * 方法描述:用户微信登录
	 * @param openid 微信对应公众号唯一标识号
	 * @return User 用户详细信息
	 * @throws SPTException
	 * date:2015-6-1
	 * @author wangfengtong
	 */
	public User login(String openid) throws SPTException{
		User user= loginDao.login(openid);
		if(user!=null){
			user.setImagePath(user.getHeadImage());
			user.setHeadImage(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN)+user.getHeadImage());
		}
		return user;
	}
	
	
	/**
	 * 
	 * 方法描述: 发送验证码
	 * @param mobilePhone手机号 code 验证码
	 * @return 是否成功
	 * date:2015年1月9日
	 * add by: Cooper
	 * @throws SPTException 
	 */
	public int sendMsg(String mobilePhone,String code) throws SPTException{
		return loginDao.sendMsg(mobilePhone, code);
	}
	
	/**
	 * 查看验证码是否正确
	 * @param mobilePhone
	 * @param code
	 * @return Boolean
	 * @throws SPTException
	 */
	public Boolean checkMsgCorrect(String mobilePhone, String code) throws SPTException{
		return loginDao.checkMsgCorrect(mobilePhone, code);
	}
	
	/**
	 * 
	 * 方法描述: 验证通过
	 * @param mobilePhone手机号
	 * @return 是否成功
	 * date:2015年1月9日
	 * add by: Cooper
	 * @throws SPTException 
	 */
	public boolean msgValidateOver(String mobilePhone,String code) throws SPTException{
		return loginDao.msgValidateOver(mobilePhone, code);
	}
	
	/**
	 * 查看注册码是否过期
	 * @param paramMap
	 * @return Boolean
	 * @author Cooper
	 * @since 2015-01-15
	 */
	public int checkVCodeIsOutTime(String mobilePhone,String code) throws SPTException{
		return loginDao.checkVCodeIsOutTime( mobilePhone, code);
	}

	/**
	 * 方法描述：删除同一手机未验证的验证码
	 * @param mobilePhone
	 * @return Boolean
	 * @throws SPTException
	 * @date 2015-01-26
	 * @author zhoujie
	 */
	@Override
	public Boolean deleteMessage(String mobilePhone) throws SPTException {
		return loginDao.deleteMessage(mobilePhone);
	}
}
