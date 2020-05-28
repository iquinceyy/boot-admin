package com.qianfeng.boot.config.shiro;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Author quincey
 * Date 2020/5/28 16:59
 */
@Configuration
public class ShiroConfig  {

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * shiro的生命周期管理,可以自定义配置
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){// 控制器注解的使用需要配置
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }
    /**
     * 配置缓存管理器，单机版的用ecache组件，集群版用Redis,其实单机版也可以用redis
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {// 2、缓存配置
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    /**
     * 可以自定义  SessionId 生成器 给SessionDAO用的
     *
     * @return
     */
    public SessionIdGenerator sessionIdGenerator(){
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 对ShiSession的状态的增删改查
     *
     * @param sessionIdGenerator
     * @return
     */
    public SessionDAO sessionDAO(SessionIdGenerator sessionIdGenerator){
        // 4
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();// 企业级缓存绘画sessionDao
        // 配置Shiro的名字，实际上没有多大个作用
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return sessionDAO;
    }
    // 配置记住我的功能
    public RememberMeManager rememberMeManager(){
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        SimpleCookie cookie = new SimpleCookie("qc-RememberMe");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7*24*60*60*1000);
        rememberMeManager.setCookie(cookie);
        return rememberMeManager;
    }
    /**
     * 开启线程去循环检测Session的状态的
     * 建议大家用这个
     *
     * @return
     */

    public ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        sessionValidationScheduler.setInterval(30 * 60 * 1000);// 设置循环检测的时间
        return sessionValidationScheduler;
    }
    /**
     * 指的是ShiSession的会话管理员，它去管理者ShiroSession状态
     *
     * @param sessionDAO
     * @param sessionValidationScheduler
     * @return
     */
    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(SessionDAO sessionDAO,
                                                             ExecutorServiceSessionValidationScheduler sessionValidationScheduler) { // 6
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        sessionManager.setGlobalSessionTimeout(30 * 60 * 1000);// 设置Session全局过期时间

        sessionManager.setDeleteInvalidSessions(true);// 如果检测到过期就删除Session

        sessionManager.setSessionValidationScheduler(sessionValidationScheduler);// 设置循环检测器

        sessionManager.setSessionValidationSchedulerEnabled(true);// 开启循环检测

        sessionManager.setSessionDAO(sessionDAO);// 设置SessionDAO

        SimpleCookie sessionIdCookie = new SimpleCookie("qf-java-session-id");//设置一个SessionCookie

        sessionIdCookie.setHttpOnly(true);// 仅支持Http

        sessionIdCookie.setMaxAge(-1);// -1永不过期，0表示删除

        sessionManager.setSessionIdCookie(sessionIdCookie);

        sessionManager.setSessionIdUrlRewritingEnabled(false);// 不把SessionId放到浏览器地址栏中

        sessionManager.setSessionIdCookieEnabled(true);// 开启SessCookie
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Realm userRealm, EhCacheManager cacheManager,
                                                     SessionManager sessionManager, RememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }


    /**
     * 对Shiro的配置进行切面管理
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor( DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }
    //    自定义注销登录之后做的事情
    public LogoutFilter getLogoutFilter() { // 在ShiroFilterFactoryBean中使用
        LogoutFilter logoutFilter = new LogoutFilter();// 退出登录过滤器做了点手脚
        logoutFilter.setRedirectUrl("/logout");    //  自定义退出登录之后，跳转访问的方法路径
        return logoutFilter;
    }

    /**
     * [掌握！]这个是配置拦截路径的一个类
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //1.必须设置 SecuriManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/loginPage"); //登录错误或者被拦截之后，去访问的路径（方法）。而不是页面
        shiroFilterFactoryBean.setSuccessUrl(".pages/back/loginSuccess");//成功后的路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthPage"); // 没有授权，后执行的方法，在控制器上的注解没有授权，没有角色或者权限，抛出异常之后，跳转的地址

        Map<String, Filter> filtes = new HashMap<>();//map的key就是过滤器的名字
        //增加自定义过滤器
        filtes.put("logout",this.getLogoutFilter());
        //把这些过滤器交给shiro控制，就会覆盖他自带的过滤器，采用你配置的过滤器
        shiroFilterFactoryBean.setFilters(filtes);
        // 这里其实 分为上下两部分，上面部分是配置过滤器的，也就是说有哪些过滤器形成的链条

        // 配置哪个过滤器过滤哪个路径，而且顺序是从上往下匹配
        Map<String,String> filterChainDefinitionMap = new HashMap<>();
        //        配置拦截路径用的，也就是说哪些路径应该经过哪些过滤器处理
//        key:指的是路径
//        value:指的是过滤器的名称简写
        filterChainDefinitionMap.put("/logout","logout");

        //        filterChainDefinitionMap.put("/login", "user");
        // 定义内置登录处理,表单需要加method=post
        filterChainDefinitionMap.put("/pages/back/**", "authc");
        // 这些路径全部需要登录之后，才能访问
        filterChainDefinitionMap.put("/**", "anon");// 所有路径不需要任何检测，即可访问

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 配合thymeleaf使用shiro标签的，与上面无关。
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}





















