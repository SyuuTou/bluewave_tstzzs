package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "elegant_service_participate_feedback_images")
public class ElegantServiceParticipateFeedbackImages {
    @Id
    @Column(name = "elegant_service_participate_id")
    private Integer elegantServiceParticipateId;

    @Id
    private String url;

    /**
     * @return elegant_service_participate_id
     */
    public Integer getElegantServiceParticipateId() {
        return elegantServiceParticipateId;
    }

    /**
     * @param elegantServiceParticipateId
     */
    public void setElegantServiceParticipateId(Integer elegantServiceParticipateId) {
        this.elegantServiceParticipateId = elegantServiceParticipateId;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}