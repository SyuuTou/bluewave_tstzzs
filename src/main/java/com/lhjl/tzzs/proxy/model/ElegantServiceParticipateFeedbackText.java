package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "elegant_service_participate_feedback_text")
public class ElegantServiceParticipateFeedbackText {
    @Id
    @Column(name = "elegant_service_participate_id")
    private Integer elegantServiceParticipateId;

    private String description;

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
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}