package com.chinacreator.c2.flow.main;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartUp {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext(
				"classpath:custom/c2-flow-core-application.xml");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
