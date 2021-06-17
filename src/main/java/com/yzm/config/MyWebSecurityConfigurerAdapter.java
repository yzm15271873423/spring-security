package com.yzm.config;

import com.yzm.exception.MyAccessDeniedHandler;
import com.yzm.security.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/showLogin")  //登录页面
                .loginProcessingUrl("/login")  //登录页面表单提交地址，此地址可以不真实存在。当发现/login时认为是登录，需要执行UserDetailsServiceImpl
                //.successForwardUrl("/showMain")  //登录成功后跳转地址
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.sendRedirect("/showMain");
                    }
                })
                //.failureForwardUrl("/showFail") //登录失败后跳转地址
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.sendRedirect("/showFail");
                    }
                })
                .usernameParameter("myusername") //账户参数名
                .passwordParameter("mypassword");  //密码参数名

        http.authorizeRequests()
                .antMatchers("/showLogin","/showFail","/demo").permitAll() //showLogin,showfail不需要被认证，即可访问
                .antMatchers("/js/**").permitAll()  //允许访问/js/**下的资源
                .antMatchers("/abc").denyAll()   //不需要认证,当访问/abc时，返回403异常。
                .antMatchers("/authority").hasAuthority("demo:update")   //hasauthority（） 权限控制，成功登录后，需要特定权限才能访问的页面
                //.antMatchers("/authority").hasAnyAuthority("admin1","admiN")  //只要有其中一个权限就能访问
                .antMatchers("/def").hasRole("管理员")  //hasRole（） 权限控制，成功登录后，只有管理员权限才能访问的页面
                //.antMatchers("/def").access("@myServiceImpl.hasPermission(request,authentication)")  //自定义权限控制，管理员权限才能访问
                //.antMatchers("/def").hasAnyRole("def","dfds") //只要有其中一个角色权限就能访问
                .antMatchers("ip").hasIpAddress("192.168.31.65")  //ip权限控制，只要使用指定IP才能访问的页面
                //.regexMatchers("/js/.*").permitAll()
                .anyRequest().authenticated(); //所有的请求都必须被认证。必须登录后才能访问。

        //异常处理
        http.exceptionHandling()
                //.accessDeniedHandler(myAccessDeniedHandler);  //由于403异常界面对用户不友好，转到自定义处理器。异步处理。
                  .accessDeniedPage("/AccessDenied");   //同步方式，处理403异常，展示自定义异常页面

        //remember me
        http.rememberMe()
                .userDetailsService(userDetailsService)  //登录逻辑交给哪个对象
                .tokenValiditySeconds(30)  //设置失效时间，秒
                .tokenRepository(persistentTokenRepository);  //持久层对象

        http.logout()
                .logoutSuccessUrl("/showLogin")  //退出成功后跳转到/login?logout,修改跳转到/showLogin
                .logoutUrl("/yzm");  //默认的退出url为/logout,一般都不会修改，修改后前端页面退出超链接url也要相应改变。

        //关闭csrf防护
        //http.csrf().disable();

    }
}
