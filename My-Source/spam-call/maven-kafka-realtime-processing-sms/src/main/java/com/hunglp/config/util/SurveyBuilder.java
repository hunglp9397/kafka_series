package com.hunglp.config.util;

import com.hunglp.config.model.CallingInfo;
import com.hunglp.config.model.UssdSurvey;

public class SurveyBuilder {

    public static String buildContentSendSurvey(String caller){
        return "Thuê bao " + caller + " Có phải thuê bao làm phiền quý khách không?";
    }

    public static UssdSurvey generateSurvey(Integer id, CallingInfo callingInfo){
        UssdSurvey ussdSurvey = new UssdSurvey();
        ussdSurvey.setId(id);
        ussdSurvey.setCaller(callingInfo.getCaller());
        ussdSurvey.setCalled(callingInfo.getCalled());
        ussdSurvey.setContent("Thuê bao " + callingInfo.getCaller() + " Có phải thuê bao làm phiền quý khách không?");
        ussdSurvey.setResultCode(null);
        return ussdSurvey;
    }
}
