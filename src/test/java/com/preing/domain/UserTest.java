package com.preing.domain;

import org.junit.Test;

import com.preing.domain.consumer.Consumer;
import com.preing.domain.consumer.Consumer.Gender;
import com.preing.util.JSON;

public class UserTest {

	@Test
	public void test(){
		Consumer user = new Consumer("", "", "", "", "", Gender.MAN);
		System.out.println(JSON.encode(user));
	} 
}
