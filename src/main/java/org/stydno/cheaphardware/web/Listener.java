package org.stydno.cheaphardware.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.stydno.cheaphardware.service.AppService;

public class Listener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        HttpSession session = se.getSession();
        try {
            System.out.println("Session created: " + session);
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
            AppService service = (AppService) context.getBean("service");
            session.setAttribute("service", service);
        } catch (Exception e) {
            System.out.println("Error in setting session attribute: "
                    + e.getMessage());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("Session invalidated: " + session);
    }
}