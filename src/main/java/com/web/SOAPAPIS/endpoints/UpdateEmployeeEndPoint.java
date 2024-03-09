package com.web.SOAPAPIS.endpoints;

import allapis.soapapis.web.com.updateemployee.ServiceStatus;
import allapis.soapapis.web.com.getemployee.EmployeeInfo;
import allapis.soapapis.web.com.getemployee.GetEmployeeRequest;
import allapis.soapapis.web.com.getemployee.GetEmployeeResponse;
import allapis.soapapis.web.com.updateemployee.UpdateEmployeeRequest;
import allapis.soapapis.web.com.updateemployee.UpdateEmployeeResponse;
import com.web.SOAPAPIS.config.SecurityConfig;
import com.web.SOAPAPIS.model.Employee;
import com.web.SOAPAPIS.model.UserData;
import com.web.SOAPAPIS.repository.UserDataRepository;
import com.web.SOAPAPIS.services.EmployeeService;
import jakarta.annotation.Resource;
import jakarta.jws.WebParam;
import jakarta.jws.soap.SOAPMessageHandler;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.Holder;
import jakarta.xml.ws.WebServiceContext;
import lombok.RequiredArgsConstructor;
import org.apache.wss4j.dom.engine.WSSecurityEngineResult;
import org.apache.wss4j.dom.handler.WSHandlerResult;
import org.apache.wss4j.common.principal.WSUsernameTokenPrincipalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import javax.wsdl.Message;
import java.util.List;

@RequiredArgsConstructor
@Endpoint
public class UpdateEmployeeEndPoint {
    public static final String NAMESPACE_URI = "http://com.web.SOAPAPIS.allapis";

    private final EmployeeService employeeService;
    private final UserDataRepository userDataRepository;

    // this is how you can autowire Application context either put autowire annotation without final
    // or private final without autowire
    private final ApplicationContext context;

    @Qualifier("loadUsers")
    private final SimplePasswordValidationCallbackHandler simplePasswordValidationCallbackHandler;


    @PayloadRoot(namespace = NAMESPACE_URI+"/updateEmployee", localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest updateEmployeeRequest, MessageContext message){
        // below comment how to get request from message and get any attribute
        //((SaajSoapMessage) ((DefaultMessageContext) message).request).getSaajMessage().getSOAPBody().getElementsByTagName("upd:employeeId").item(0).getTextContent()
        simplePasswordValidationCallbackHandler.setUsersMap(userDataRepository.getAllUsers());
        String username = SecurityConfig.getAuthName(message);
        UpdateEmployeeResponse updateEmployeeResponse = new UpdateEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Employee employee = new Employee();
        employee.setEmployeeId(updateEmployeeRequest.getEmployeeInfo().getEmployeeId());
        employee.setName(updateEmployeeRequest.getEmployeeInfo().getName());
        employee.setDepartment(updateEmployeeRequest.getEmployeeInfo().getDepartment());
        employee.setAddress(updateEmployeeRequest.getEmployeeInfo().getAddress());
        employee.setPhone(updateEmployeeRequest.getEmployeeInfo().getPhone());
        employeeService.updateEmployee(employee);
        serviceStatus.setStatus("success");
        serviceStatus.setMessage("updated successfully");
        updateEmployeeResponse.setServiceStatus(serviceStatus);
        return updateEmployeeResponse;
    }
}
