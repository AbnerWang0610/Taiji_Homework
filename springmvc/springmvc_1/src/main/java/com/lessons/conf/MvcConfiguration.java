package com.lessons.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.lessons.MyConvert;
import com.lessons.inteceptor.LoginInteceptor;
import com.lessons.inteceptor.TimeInteceptor;

@Configuration
@EnableWebMvc
@ComponentScan("com.lessons.controller")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	/*@Bean("localeResolver")
	public LocaleResolver cookieLocaleResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName("cookieLocale");
		return localeResolver;
	}*/
	
	@Bean("localeResolver")
	public SessionLocaleResolver session() {
		return new SessionLocaleResolver();
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		return messageSource;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new LoginInteceptor());
		registry.addInterceptor(new TimeInteceptor());
		LocaleChangeInterceptor lr = new LocaleChangeInterceptor();
		lr.setParamName("lang");
		registry.addInterceptor(lr);
		
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/jsp/", ".jsp");
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/statics/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/statics/js/");
	}
}
