package com.pieterjd.aemadmin.shell.utils;

import com.pieterjd.aemadmin.config.ConfigFactory;
import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class ConnectionPromptProvider implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        Properties config = ConfigFactory.getConfig();
        boolean connected = true;

        String msg = String.format("AEM Admin ");
        msg += connected
                ? String.format("(%s:%s)> ",config.getProperty("baseUrl"),config.getProperty("port"))
                : "(not connected)> ";

        return new AttributedString(msg);
    }
}
