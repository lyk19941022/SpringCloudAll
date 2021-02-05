package com.liuyukun.springsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        // TODO Auto-generated method stub
        http.authorizeRequests()
                //所有请求都需要验证
                .anyRequest().authenticated()
                .and()
                //permitAll 给没登录的 用户可以访问这个地址的权限
                .formLogin().loginPage("/login.html")
                //自定义表单
               /* .usernameParameter("xx")
                .passwordParameter("oo")
*/
                .loginProcessingUrl("/login")
                .failureUrl("/login.html?error")
                .defaultSuccessUrl("/").permitAll()
                .failureHandler(new AuthenticationFailureHandler() {

                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                        AuthenticationException exception) throws IOException, ServletException {
                        // TODO Auto-generated method stub
                        exception.printStackTrace();

                        // 判断异常信息 跳转不同页面 比如 密码过期重置
                        request.getRequestDispatcher(request.getRequestURL().toString()).forward(request, response);

                        // 记录登录失败次数 禁止登录

                    }
                })
                .and()
                .csrf()
                //默认所有的post请求都会拦截
                //.disable();
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository());
    }

}
