package com.example.SMSService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service

public class Service {
    private final SMSSender smsSender;

    @Autowired
    public Service(@Qualifier("twilio") TwilioSMSSender twilioSMSSender) {
        this.smsSender = twilioSMSSender;
    }

    public  void sendSms(SmsRequest smsRequest){
        smsSender.sendSMS(smsRequest);
    }
}
