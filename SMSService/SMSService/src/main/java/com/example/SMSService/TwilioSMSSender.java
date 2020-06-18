package com.example.SMSService;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;


@Service("twilio")
public class TwilioSMSSender implements SMSSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSMSSender.class);

    private final TwilioConfiguration twilioConfiguration;

    public TwilioSMSSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSMS(SmsRequest smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber("+12565677501");

            String message = smsRequest.getMessage();

            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}" + smsRequest);
        } else {
            throw new IllegalArgumentException("Phone number [" + smsRequest.getPhoneNumber() + " is not a valid phone number");
        }

    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        //can implement Phone number validator by using google api
        return true;
    }
}
