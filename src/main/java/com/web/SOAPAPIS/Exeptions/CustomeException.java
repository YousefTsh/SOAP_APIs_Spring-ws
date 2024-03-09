package com.web.SOAPAPIS.Exeptions;

import lombok.Data;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;


// this how you define and handle exceptions in SOAP APIs you just have to cretate your custome exception
//and throw it without need to add handlers like rest
//you just add soap fault to add fultcode
@Data
@SoapFault(faultCode = FaultCode.CUSTOM,customFaultCode = "{http://com.web.SOAPAPIS.allapis}CMS.R456")
public class CustomeException extends RuntimeException{
    private final String message;
}
