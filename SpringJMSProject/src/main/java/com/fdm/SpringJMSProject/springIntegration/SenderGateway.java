package com.fdm.SpringJMSProject.springIntegration;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.Header;

public interface SenderGateway {

    @Gateway
    public void sendMyMessage(@Header("myHeaderKey") String s, Object o);

}
