package com.web.SOAPAPIS.config;

import org.apache.wss4j.common.principal.WSUsernameTokenPrincipalImpl;
import org.apache.wss4j.dom.engine.WSSecurityEngineResult;
import org.apache.wss4j.dom.handler.WSHandlerResult;
import org.springframework.ws.context.MessageContext;

import java.util.List;

public class SecurityConfig {

    public static String getAuthName(MessageContext message){
        //this block is how you can get security context from message and extract user name to use it in any other commands
        List sec = (List) message.getProperty("RECV_RESULTS");
        List<WSSecurityEngineResult> result =((WSHandlerResult) sec.get(0)).getResults();
        WSUsernameTokenPrincipalImpl username  = (WSUsernameTokenPrincipalImpl) result.get(0).get("principal");
        String user = username.getName();
        return user;
    }
}
