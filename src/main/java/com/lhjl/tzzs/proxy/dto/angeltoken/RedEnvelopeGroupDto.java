package com.lhjl.tzzs.proxy.dto.angeltoken;

public class RedEnvelopeGroupDto {

    /**
     * 微信获取
     */
    private String encryptedData;
    /**
     * 微信获取
     */
    private String iv;
    /**
     * 微信群唯一key
     */
    private String unionKey;

    public String getUnionKey() {
        return unionKey;
    }

    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
