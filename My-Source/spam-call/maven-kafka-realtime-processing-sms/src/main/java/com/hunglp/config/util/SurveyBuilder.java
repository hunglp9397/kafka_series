package com.hunglp.config.util;

import com.hunglp.config.model.CallingInfo;
import com.hunglp.config.model.UssdSurvey;

import java.util.Random;

public class SurveyBuilder {



    public static UssdSurvey generateSurvey(Integer id, CallingInfo callingInfo){
        UssdSurvey ussdSurvey = new UssdSurvey();
        ussdSurvey.setId(id);
        ussdSurvey.setCaller(callingInfo.getCaller());
        ussdSurvey.setCalled(callingInfo.getCalled());
        ussdSurvey.setContent("Thuê bao " + callingInfo.getCaller() + " Có phải thuê bao làm phiền quý khách không?");

        int min=-1;
        int max=1;
        Random random=new Random();
        int randomResponseCode=random.nextInt(max-min)+min;
        ussdSurvey.setResultCode(randomResponseCode);
        return ussdSurvey;
    }

    public static void main(String[] args) {
        int min=-1;
        int max=1;
        Random random=new Random();
        int randomResponseCode=random.nextInt(max - min + 1) + min;
        System.out.println(randomResponseCode);
    }

}
