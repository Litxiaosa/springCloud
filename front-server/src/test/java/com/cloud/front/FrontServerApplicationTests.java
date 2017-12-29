package com.cloud.front;

import com.cloud.front.domain.User;
import com.cloud.front.service.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FrontServerApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(FrontServerApplicationTests.class);

	@Autowired
	private IRedisService redisService;


	@Test
	public void test(){

		//往set添加元素
		Long flag = redisService.setAdd("xiaosa", "litxiaosa.me");
		logger.info("-------------往set添加元素---------------------------"+flag);

		//set 返回指定key的成员数
		Long flag1 = redisService.getSetNumber("xiaosa");
		logger.info("--------------set 返回指定key的成员数---------------"+flag1);

		//设置 set 值
		Long flag3 =redisService.setSetValue("xiaosa", "litxiaosa@qq.com");
		logger.info("--------------设置 set 值---------------"+flag3);

		//获取set
		String flag4 = redisService.getSetValue("xiaosa");
		logger.info("--------------获取set---------------"+flag4);

		//删除指定set的指定value
		Long flag5 =redisService.delSetValue("xiaosa","litxiaosa@qq.com");
		logger.info("--------------删除指定set的指定value---------------"+flag5);

		//获取set
		String flag6 = redisService.getSetValue("xiaosa");
		logger.info("--------------获取set,看是否删掉---------------"+flag6);

		//往set添加元素
		Long flag7 = redisService.setAdd("xiaosa", "litxiaosa.me");
		logger.info("-------------往set添加元素,再次添加---------------------------"+flag7);

		//获取set
		String flag8 = redisService.getSetValue("xiaosa");
		logger.info("--------------再次获取---------------"+flag8);

		//删除key
		Long flag9 = redisService.delKey("xiaosa");
		logger.info("--------------删除key---------------"+flag9);

		//获取set
		String flag10 = redisService.getSetValue("xiaosa");
		logger.info("--------------再次获取,看是否删掉---------------"+flag10);

		//储存实体
		User user = new User();
		user.setName("隔岸");
		user.setMoblie("1123456");
		user.setEmail("416082509@qq.com");
		user.setPassword("12345");
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setInvalid(false);
		//储存实体
		redisService.setObject("gean", user, 60L);

		//取出实体
		User user1 = redisService.getObject("gean",user.getClass());
		logger.info("--------------取出实体---------------"+user1);
	}

}
