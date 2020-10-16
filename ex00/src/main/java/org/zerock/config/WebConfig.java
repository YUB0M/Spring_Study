package org.zerock.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public abstract class WebConfig extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    // XML을 사용하지 않고 이 역할을 대신하는 클래스를 작성해서 처리s
    // WebConfig래스는 Abst~~lizer의 긴 이름의 추상클래스를 상속.

    @Override
    protected Class<?>[] getRootConfigClasses(){
        //root-context.xml을 대신하는 클래스를 지정
        //return null;
        return new Class[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return null;
    }
}
