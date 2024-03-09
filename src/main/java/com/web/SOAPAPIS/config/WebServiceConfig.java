package com.web.SOAPAPIS.config;

import com.web.SOAPAPIS.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Collections;
import java.util.List;

@EnableWs
@Configuration
@RequiredArgsConstructor
public class WebServiceConfig extends WsConfigurerAdapter {
    private final UserDataRepository userDataRepository;

    @Bean(name = "loadUsers")
    public SimplePasswordValidationCallbackHandler securityCallbackHandler(){
        SimplePasswordValidationCallbackHandler callbackHandler = new SimplePasswordValidationCallbackHandler();
        callbackHandler.setUsersMap(userDataRepository.getAllUsers());
        return callbackHandler;
    }

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(){
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
        securityInterceptor.setValidationActions("UsernameToken");
        securityInterceptor.setValidationCallbackHandler(securityCallbackHandler());
        return securityInterceptor;
    }

    @Override
    public void addInterceptors(List interceptors){
        interceptors.add(securityInterceptor());
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/SOAP/Services/*");
    }


    // each bean used to defin name of WSDL and URI will used in url end point when we send request
    // and target name space all the API xml config classes
    @Bean(name = "addemployee")
    public DefaultWsdl11Definition addEmployeeWSDL(@Qualifier("addemployeebean") XsdSchema addemployeeSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AddEmployeeWSDL");
        wsdl11Definition.setLocationUri("/ws/SOAP/Services");
        wsdl11Definition.setTargetNamespace("http://com.web.SOAPAPIS.allapis/addEmployee");
        wsdl11Definition.setSchema(addemployeeSchema);
        return wsdl11Definition;
    }

    // bean to load xsd schema to be used in wsdl generation bean
    @Bean(name = "addemployeebean")
    public XsdSchema addemployeeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/addemployee.xsd"));
    }


    @Bean(name = "updateemployee")
    public DefaultWsdl11Definition updateEmployeeWSDL(@Qualifier("updateemployeebean") XsdSchema updateemployeeSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UpdateEmployeeWSDL");
        wsdl11Definition.setLocationUri("/ws/SOAP/Services");
        wsdl11Definition.setTargetNamespace("http://com.web.SOAPAPIS.allapis/updateEmployee");
        wsdl11Definition.setSchema(updateemployeeSchema);
        return wsdl11Definition;
    }

    @Bean(name = "updateemployeebean")
    public XsdSchema updateemployeeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/updateemployee.xsd"));
    }


    @Bean(name = "getemployee")
    public DefaultWsdl11Definition getEmployeeWSDL(@Qualifier("getemployeebean") XsdSchema getemployeeSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GetEmployeeWSDL");
        wsdl11Definition.setLocationUri("/ws/SOAP/Services");
        wsdl11Definition.setTargetNamespace("http://com.web.SOAPAPIS.allapis/getEmployee");
        wsdl11Definition.setSchema(getemployeeSchema);
        return wsdl11Definition;
    }

    @Bean(name = "getemployeebean")
    public XsdSchema getemployeeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/getemployee.xsd"));
    }

    @Bean(name = "deleteemployee")
    public DefaultWsdl11Definition deleteEmployeeWSDL(@Qualifier("deleteemployeebean") XsdSchema deleteemployeeSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("DeleteEmployeeWSDL");
        wsdl11Definition.setLocationUri("/ws/SOAP/Services");
        wsdl11Definition.setTargetNamespace("http://com.web.SOAPAPIS.allapis/deleteEmployee");
        wsdl11Definition.setSchema(deleteemployeeSchema);
        return wsdl11Definition;
    }

    @Bean(name = "deleteemployeebean")
    public XsdSchema deleteemployeeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/deleteemployee.xsd"));
    }

//    @Bean(name = "complex")
//    public DefaultWsdl11Definition complexWSDL(@Qualifier("complexbean") XsdSchema complexSchema){
//        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
//        wsdl11Definition.setPortTypeName("complexWSDL");
//        wsdl11Definition.setLocationUri("/ws/SOAP/Services");
//        wsdl11Definition.setTargetNamespace("http://com.web.SOAPAPIS.allapis");
//        wsdl11Definition.setSchema(complexSchema);
//        return wsdl11Definition;
//    }
//
//    @Bean(name = "complexbean")
//    public XsdSchema complexSchema() {
//        return new SimpleXsdSchema(new ClassPathResource("xsd/complex.xsd"));
//    }


}
