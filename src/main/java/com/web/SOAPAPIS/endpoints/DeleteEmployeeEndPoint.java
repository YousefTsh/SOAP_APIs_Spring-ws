package com.web.SOAPAPIS.endpoints;

import allapis.soapapis.web.com.deleteemployee.DeleteEmployeeRequest;
import allapis.soapapis.web.com.deleteemployee.DeleteEmployeeResponse;
import allapis.soapapis.web.com.deleteemployee.ServiceStatus;
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
public class DeleteEmployeeEndPoint {

    public static final String NAMESPACE_URI = "http://com.web.SOAPAPIS.allapis";

    private final EmployeeService employeeService;
    private final UserDataRepository userDataRepository;
    @Qualifier("loadUsers")
    private final SimplePasswordValidationCallbackHandler simplePasswordValidationCallbackHandler;

    @PayloadRoot(namespace = NAMESPACE_URI+"/deleteEmployee", localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest deleteEmployeeRequest){
        simplePasswordValidationCallbackHandler.setUsersMap(userDataRepository.getAllUsers());
        DeleteEmployeeResponse deleteEmployeeResponse = new DeleteEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        employeeService.deleteEmployee(deleteEmployeeRequest.getEmployeeId());
        serviceStatus.setStatus("success");
        serviceStatus.setMessage("deleted successfully");
        deleteEmployeeResponse.setServiceStatus(serviceStatus);
        return deleteEmployeeResponse;
    }
}
