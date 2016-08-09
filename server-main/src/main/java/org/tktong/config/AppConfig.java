package org.tktong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@ComponentScan({ "org.tktong.*" })
@Import({ SecurityConfig.class })
public class AppConfig {

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/trainerrank");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("shangrila07");
	    return driverManagerDataSource;
	}
	
	// @Bean
	// public InternalResourceViewResolver viewResolver() {
	//     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	//     viewResolver.setViewClass(JstlView.class);
	//     viewResolver.setPrefix("/WEB-INF/pages/");
	//     viewResolver.setSuffix(".jsp");
	//     return viewResolver;
	// }
	
}