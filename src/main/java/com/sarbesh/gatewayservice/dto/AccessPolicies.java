package com.sarbesh.gatewayservice.dto;

import java.util.List;

public class AccessPolicies {
    private List<AccessPolicy> accessPolicyList;

    public List<AccessPolicy> getAccessPolicyList() {
        return accessPolicyList;
    }

    public void setAccessPolicyList(List<AccessPolicy> accessPolicyList) {
        this.accessPolicyList = accessPolicyList;
    }

    @Override
    public String toString() {
        return "AccessPolicies{" +
                "accessPolicyList=" + accessPolicyList +
                '}';
    }

    public AccessPolicies() {
    }

    public AccessPolicies(List<AccessPolicy> accessPolicyList) {
        this.accessPolicyList = accessPolicyList;
    }
}
