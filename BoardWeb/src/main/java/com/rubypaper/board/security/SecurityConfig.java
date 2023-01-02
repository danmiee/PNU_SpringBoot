package com.rubypaper.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration		// versionUp 관련 추가 어노테이션
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private SecurityUserDetailsService userDetailsService;

	// versionUp하며 Bean으로 등록으로 변경
	@Bean
	public DefaultSecurityFilterChain configure(HttpSecurity security) throws Exception {
		security.userDetailsService(userDetailsService);

		security.authorizeRequests().antMatchers("/", "/system/**").permitAll();
		security.authorizeRequests().antMatchers("/board/**").authenticated();
		security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

		security.csrf().disable();
		security.formLogin().loginPage("/system/login").defaultSuccessUrl("/board/getBoardList", true);
		security.exceptionHandling().accessDeniedPage("/system/accessDenied");
		security.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/");
		
		return security.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
