package com.sarbesh.gatewayservice.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties
public class AccessPolicies implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessPolicies.class);

    private List<AccessPolicy> accessPolicies;

    public List<AccessPolicy> getAccessPolicies() {
        return accessPolicies;
    }

    public void setAccessPolicies(List<AccessPolicy> accessPolicies) {
        this.accessPolicies = accessPolicies;
    }

    @Override
    public String toString() {
        return "AccessPolicies{" +
                "accessPolicyList=" + accessPolicies +
                '}';
    }

    public AccessPolicies() {
    }

    public AccessPolicies(List<AccessPolicy> accessPolicies) {
        this.accessPolicies = accessPolicies;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Access policies loaded: {} ",accessPolicies);
    }
}
