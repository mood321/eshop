package com.mood.eshop.inventory.dao;


public interface RedisDAO {

	void set(String key, String value);
	
	String get(String key);

	Long  delete(String key);
	
}
