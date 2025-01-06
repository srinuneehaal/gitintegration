package com.sb.githubsbintegration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Verification{
    @JsonProperty("verified")
    public boolean getVerified() { 
		 return this.verified; } 
    public void setVerified(boolean verified) { 
		 this.verified = verified; } 
    boolean verified;
    @JsonProperty("reason") 
    public String getReason() { 
		 return this.reason; } 
    public void setReason(String reason) { 
		 this.reason = reason; } 
    String reason;
    @JsonProperty("signature") 
    public Object getSignature() { 
		 return this.signature; } 
    public void setSignature(Object signature) { 
		 this.signature = signature; } 
    Object signature;
    @JsonProperty("payload") 
    public Object getPayload() { 
		 return this.payload; } 
    public void setPayload(Object payload) { 
		 this.payload = payload; } 
    Object payload;
    @JsonProperty("verified_at") 
    public Object getVerified_at() { 
		 return this.verified_at; } 
    public void setVerified_at(Object verified_at) { 
		 this.verified_at = verified_at; } 
    Object verified_at;
}