package com.lhjl.tzzs.proxy.dto.angeltoken;

public class RedEnvelopeGroupListDto {

    /** 微信群ID */
    private String weixinGroupId;
    /** 微信群唯一KEY */
    private String weixinGroupUnionKey;
    /** 领取数量 */
    private Integer received;
    /** 未领取数量 */
    private Integer unreceived;

    public String getWeixinGroupId() {
        return weixinGroupId;
    }

    public void setWeixinGroupId(String weixinGroupId) {
        this.weixinGroupId = weixinGroupId;
    }

    public String getWeixinGroupUnionKey() {
        return weixinGroupUnionKey;
    }

    public void setWeixinGroupUnionKey(String weixinGroupUnionKey) {
        this.weixinGroupUnionKey = weixinGroupUnionKey;
    }

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
    }

    public Integer getUnreceived() {
        return unreceived;
    }

    public void setUnreceived(Integer unreceived) {
        this.unreceived = unreceived;
    }
}
