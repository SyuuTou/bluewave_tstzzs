package com.lhjl.tzzs.proxy.dto.ElegantServiceDto;

import com.lhjl.tzzs.proxy.model.ElegantServiceParticipate;
import com.lhjl.tzzs.proxy.model.ElegantServiceParticipateFeedbackImages;
import com.lhjl.tzzs.proxy.model.ElegantServiceParticipateFeedbackText;

import java.util.List;

public class ElegantServiceParticipateDto {

    private Integer elegantServiceParticipateId;
    private String token;
    private String formId;
    private ElegantServiceParticipateFeedbackText elegantServiceParticipateFeedbackText;
    private List<ElegantServiceParticipateFeedbackImages> elegantServiceParticipateFeedbackImages;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Integer getElegantServiceParticipateId() {
        return elegantServiceParticipateId;
    }

    public void setElegantServiceParticipateId(Integer elegantServiceParticipateId) {
        this.elegantServiceParticipateId = elegantServiceParticipateId;
    }

    public ElegantServiceParticipateFeedbackText getElegantServiceParticipateFeedbackText() {
        return elegantServiceParticipateFeedbackText;
    }

    public void setElegantServiceParticipateFeedbackText(ElegantServiceParticipateFeedbackText elegantServiceParticipateFeedbackText) {
        this.elegantServiceParticipateFeedbackText = elegantServiceParticipateFeedbackText;
    }

    public List<ElegantServiceParticipateFeedbackImages> getElegantServiceParticipateFeedbackImages() {
        return elegantServiceParticipateFeedbackImages;
    }

    public void setElegantServiceParticipateFeedbackImages(List<ElegantServiceParticipateFeedbackImages> elegantServiceParticipateFeedbackImages) {
        this.elegantServiceParticipateFeedbackImages = elegantServiceParticipateFeedbackImages;
    }
}
