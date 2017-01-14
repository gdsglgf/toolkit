package com.example.myproject.page3;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 加载配置文件数据到类静态属性中
 */
@Component
public class Constants {
	public static int draw;
	public static int start;
	public static int length;
	
	@Value("${page.draw:1}")
	public void setDraw(int draw) {
		Constants.draw = draw;
	}
	
	@Value("${page.start:0}")
	public void setStart(int start) {
		Constants.start = start;
	}

	@Value("${page.length:10}")
	public void setLength(int length) {
		Constants.length = length;
	}
	
	@Value("${ip:192.168.0.1}")
	public void setIP(String ip) {
		System.out.printf("IP:[%s]\n", ip);
	}
	
	@Value("#{'${server.name}'.split(',')}")
	public void setServer(List<String> servers) {
		servers.forEach(System.out::println);
	}
	
	@Value("#{'${server.id}'.split(',')}")
	public void setServerId(List<String> serverId) {
		serverId.forEach(System.out::println);
	}
	
	@Value("#{ systemProperties['user.home'] }")
	public void setUserHome(String userHome) {
		System.out.printf("userHome:[%s]\n", userHome);
	}
	
	@Value("#{ systemProperties['user.region'] ?: 'en_US' }")
	public void setDefaultLocale(String defaultLocale) {
		System.out.printf("defaultLocale:[%s]\n", defaultLocale);
	}
	
	@Value("#{ systemProperties }")
	public void showSystemProperties(Properties p) {
		showProperties(p);
	}
	
	public static void showProperties(Properties p) {
		Set<Entry<Object, Object>> entrys = p.entrySet();
		System.out.println("--------------------------");
		for (Entry<Object, Object> ent : entrys) {
			System.out.println(ent.getKey() + ":" + ent.getValue());
		}
		System.out.println("--------------------------");
	}
	
	public static void ShowMap(Map<String, String> m) {
		System.out.println("--------------------------");
		for (Entry<String, String> ent : m.entrySet()) {
			System.out.println(ent.getKey() + ":" + ent.getValue());
		}
		System.out.println("--------------------------");
	}
	
	public static void main(String[] args) {
		showProperties(System.getProperties());
		ShowMap(System.getenv());
	}
}
