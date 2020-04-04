package cn.hotel.hotelserver.config.security;

import cn.hotel.hotelserver.config.security.handler.AuthenticationFailureHandler;
import cn.hotel.hotelserver.config.security.handler.UserLoginFailureHandler;
import cn.hotel.hotelserver.config.security.handler.UserLoginSuccessHandler;
import cn.hotel.hotelserver.config.security.handler.UserLogoutSuccessHandler;
import cn.hotel.hotelserver.config.security.jwt.JWTAuthenticationTokenFilter;
import cn.hotel.hotelserver.service.basic.AdminService;
import cn.hotel.hotelserver.util.upload.UploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AdminService adminService;

    @Autowired
    UserLoginSuccessHandler userLoginSuccessHandler;

    @Autowired
    UserLoginFailureHandler userLoginFailureHandler;

    @Autowired
    UserLogoutSuccessHandler userLogoutSuccessHandler;

    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    UploadProperties uploadProperties;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 本地上传文件路径可访问
        String uploadPath = uploadProperties.getLocal().getServerPath() + "/**";
        web.ignoring().antMatchers(uploadPath);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() //全部请求都需要严重
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .successHandler(userLoginSuccessHandler)
                .failureHandler(userLoginFailureHandler)
                .and()
                .logout()
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .and()
                .exceptionHandling()
                //请求失败处理回调
                .authenticationEntryPoint(authenticationFailureHandler)
                .and()
                .csrf().disable(); //关闭csrf，让postman可以调试

        http.addFilter(new JWTAuthenticationTokenFilter(authenticationManager()));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminService);
    }
}
