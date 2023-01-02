package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private BoardUserDetailsService boardUserDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		/*
		 * csrf : 인터넷 접근정책 - 다른 ip의 접근불가 설정 disable : 허용 / default는 허용안함이기 때문에 허용하기 위한
		 * 설정 필요
		 */
		http.csrf().disable();

		// Role에 따른 접근가능여부 설정
		http.authorizeHttpRequests()
				// 로그인하면 접근 가능
				.requestMatchers("/member/**").authenticated()
				// manager, admin 접근 가능
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				// admin 접근 가능
				.requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll();

		// 로그인을 위한 폼 사용
		http.formLogin()
				// 사용자가 작성한 로그인페이지로 이동
				.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true);
		
		http.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/loginSuccess",true);
		
		http.exceptionHandling()
			.accessDeniedPage("/accessDenied");
		
		http.userDetailsService(boardUserDetailsService);

		return http.build();
	}

//	@Autowired
//	private DataSource dataSource;
//	
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//
//		/*
//		 * security 내부의 field명은 username, password로 고정되어있음
//		 * 내가 다른 이름을 사용했다고 하더라도 security 내부에서는 정해진 이름으로 찾음
//		 */
//		
//		// 인증받기
//		String query1 = "select id username, concat('{noop}', password) password, "
//				+ "true enabled from member where id=?";
//		
//		// 접속권한 확인
//		String query2 = "select id, role from member where id=?";
//		
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery(query1)
//			.authoritiesByUsernameQuery(query2);
		
		// db 연결 전
		// {noop} : 암호화 안함
//		auth.inMemoryAuthentication()
//			.withUser("manager")
//			.password("{noop}manager123")
//			.roles("MANAGER");
//
//		auth.inMemoryAuthentication()
//			.withUser("admin")
//			.password("{noop}admin123")
//			.roles("ADMIN");
		
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
