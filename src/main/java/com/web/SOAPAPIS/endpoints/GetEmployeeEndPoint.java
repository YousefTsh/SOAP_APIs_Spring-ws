package com.web.SOAPAPIS.endpoints;

import allapis.soapapis.web.com.getemployee.EmployeeInfo;
import allapis.soapapis.web.com.getemployee.GetEmployeeRequest;
import allapis.soapapis.web.com.getemployee.GetEmployeeResponse;
import com.web.SOAPAPIS.repository.UserDataRepository;
import com.web.SOAPAPIS.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;

@RequiredArgsConstructor
@Endpoint
public class GetEmployeeEndPoint {
    public static final String NAMESPACE_URI = "http://com.web.SOAPAPIS.allapis";

    private final EmployeeService employeeService;
    private final UserDataRepository userDataRepository;
    @Qualifier("loadUsers")
    private final SimplePasswordValidationCallbackHandler simplePasswordValidationCallbackHandler;

    @PayloadRoot(namespace = NAMESPACE_URI+"/getEmployee", localPart = "getEmployeeRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest getEmployeeRequest){
        simplePasswordValidationCallbackHandler.setUsersMap(userDataRepository.getAllUsers());
        GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();
        EmployeeInfo employeeInfo= employeeService.getEmployeeById(getEmployeeRequest.getEmployeeId());
        getEmployeeResponse.setEmployeeInfo(employeeInfo);
        return getEmployeeResponse;
    }
}
