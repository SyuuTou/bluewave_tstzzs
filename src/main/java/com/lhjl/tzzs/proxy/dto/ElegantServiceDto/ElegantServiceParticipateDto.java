package com.lhjl.tzzs.proxy.dto.ElegantServiceDto;

import com.lhjl.tzzs.proxy.model.ElegantServiceParticipate;
import com.lhjl.tzzs.proxy.model.ElegantServiceParticipateFeedbackImages;
import com.lhjl.tzzs.proxy.model.ElegantServiceParticipateFeedbackText;

import java.util.List;

public class ElegantServiceParticipateDto {

    private ElegantServiceParticipate elegantServiceParticipate;
    private ElegantServiceParticipateFeedbackText elegantServiceParticipateFeedbackText;
    private List<ElegantServiceParticipateFeedbackImages> elegantServiceParticipateFeedbackImages;


    public ElegantServiceParticipate getElegantServiceParticipate() {
        return elegantServiceParticipate;
    }

    public void setElegantServiceParticipate(ElegantServiceParticipate elegantServiceParticipate) {
        this.elegantServiceParticipate = elegantServiceParticipate;
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
