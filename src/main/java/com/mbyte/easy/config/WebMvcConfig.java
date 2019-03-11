package com.mbyte.easy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring mvc 配置
 * @author nicc
 *
 */
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/admin/login").setViewName("login");
//		registry.addViewController("/welcome").setViewName("welcome");
	}



}
