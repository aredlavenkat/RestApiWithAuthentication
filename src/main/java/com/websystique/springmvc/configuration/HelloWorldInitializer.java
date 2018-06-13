package com.websystique.springmvc.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.websystique.springmvc.security.ApiSecurityConfiguration;

/**
* Spring Web MVC Security Java Config Demo Project
* Bootstraps Spring Dispacher Servlet in Servlet 3.0+ environment.
*
* @author www.codejava.net
*
*/
public class HelloWorldInitializer implements WebApplicationInitializer {

   @Override
   public void onStartup(ServletContext servletContext) throws ServletException {
       AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
       appContext.register(ApiSecurityConfiguration.class);
       ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher", new DispatcherServlet(appContext));
       dispatcher.setLoadOnStartup(1);
       dispatcher.addMapping("/*");
   }

    
}