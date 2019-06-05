package cn.nupt.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描mybatis mapper 的包路径
@MapperScan(basePackages = "cn.nupt.wechat.mapper")

@ComponentScan(basePackages = {"cn.nupt.wechat","org.n3r.idworker"})

public class WechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatApplication.class, args);
	}

}
