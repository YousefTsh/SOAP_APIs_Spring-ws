package com.web.SOAPAPIS.endpoints;

import allapis.soapapis.web.com.addemployee.AddEmployeeRequest;
import allapis.soapapis.web.com.addemployee.AddEmployeeResponse;
import allapis.soapapis.web.com.addemployee.ServiceStatus;
import allapis.soapapis.web.com.deleteemployee.DeleteEmployeeRequest;
import allapis.soapapis.web.com.deleteemployee.DeleteEmployeeResponse;
import com.web.SOAPAPIS.model.Employee;
import com.web.SOAPAPIS.repository.UserDataRepository;
import com.web.SOAPAPIS.services.EmployeeService;
import jakarta.xml.bind.JAXBElement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;

import javax.xml.namespace.QName;

//each Endpoint should represent 1 API
@RequiredArgsConstructor
@Endpoint
public class AddEmployeeEndPoint {

    //general namspace that will hold all the APIS
    public static final String NAMESPACE_URI = "http://com.web.SOAPAPIS.allapis";

    private final EmployeeService employeeService;
    private final UserDataRepository userDataRepository;
    @Qualifier("loadUsers")
    private final SimplePasswordValidationCallbackHandler simplePasswordValidationCallbackHandler;

    // each api add to name space its classes location to match xsd file
    @PayloadRoot(namespace = NAMESPACE_URI+"/addEmployee", localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request){
        simplePasswordValidationCallbackHandler.setUsersMap(userDataRepository.getAllUsers());
        AddEmployeeResponse addEmployeeResponse = new AddEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Employee employee = new Employee();
        employee.setName(request.getEmployeeInfo().getName());
        employee.setDepartment(request.getEmployeeInfo().getDepartment());
        employee.setAddress(request.getEmployeeInfo().getAddress());
        employee.setPhone(request.getEmployeeInfo().getPhone());
//        BeanUtils.copyProperties(request.getEmployeeInfo(),employee);// same as you set evey value by your self
        employeeService.AddEmployee(employee);
        serviceStatus.setStatus("success");
        serviceStatus.setMessage("added successfully");
        addEmployeeResponse.setServiceStatus(serviceStatus);
        return addEmployeeResponse;
    }



    private <T> JAXBElement createResponseJaxbElement(T object, Class clazz) {
        return new JAXBElement<>(new QName(NAMESPACE_URI, clazz.getSimpleName()), clazz, object);

    }
}
