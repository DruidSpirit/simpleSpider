/** **/
package com.ydj.common.dao;

import java.util.List;

import net.sf.json.JSONObject;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2013-8-15 下午09:17:31
 * @version : 1.0
 * @description :
 *
 */
public interface MyDao {

	public void save(int typeOf,String company,String storeURL,String mainProduct,String address,String bussModel,String contact,String tel);
	
	public List<JSONObject> getList()throws Exception;
	
	public int update(int id,String contact,String tel);
	
	public int getAllCount();
	
	public List<JSONObject> getUserInfoList()throws Exception;
	
	public int updateUserConfig(String userAgent,String cookie);
	
}
