package com.heartiger.zhenaiparser;

import com.heartiger.model.ParseResult;
import com.heartiger.model.Profile;
import com.heartiger.shared.Parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileParser implements Parser {

    private final static Pattern basicInfo = Pattern
            .compile("\"basicInfo\":\\[[^]]+]");

    private final static Pattern marriageInfo = Pattern
            .compile("\"basicInfo\":\\[\"([^\"]+)\",\"[0-9]{2}岁\",[^]]*]");

    private final static Pattern ageInfo = Pattern
            .compile("\"basicInfo\":\\[.*\"([0-9]{2})岁\"[^]]*]");

    private final static Pattern constellationInfo = Pattern
            .compile("\"basicInfo\":\\[.*\"([^(]+座)[^\"]*\"[^]]*]");

    private final static Pattern heightInfo = Pattern
            .compile("\"basicInfo\":\\[.*\"([0-9]{3}cm)\"[^]]*]");

    private final static Pattern weightInfo = Pattern
            .compile("\"basicInfo\":\\[.*\"([0-9]{2,3}kg)\"[^]]*]");

    private final static Pattern workPlaceIncomeInfo = Pattern
            .compile("\"basicInfo\":\\[.*\"工作地:([^\"]+)\",\"月收入:([^\"]+)\"[^]]*]");

    private final static Pattern workEducationInfo = Pattern
            .compile("\"basicInfo\":\\[.*\"工作地:[^\"]+\",\"月收入:[^\"]+\",\"([^\"]+)\",\"([^\"]+)\"[^]]*]");

    private final static Pattern detailInfo = Pattern
            .compile("\"detailInfo\":\\[[^]]+]");

    private final static Pattern resKidInfo = Pattern
            .compile("\"detailInfo\":\\[.*\"籍贯:([^\"]+)\",.*\"是否想要孩子:([^\"]+)\".*]");

    private final static Pattern coreInfo = Pattern
            .compile("\"genderString\":\"([^\"]+)\".+,\"nickname\":\"([^\"]+)\"");

    private final static Pattern carInfo = Pattern
            .compile("\"detailInfo\":\\[.*,\"(已买车)\",.*]");

    private final static Pattern houseInfo = Pattern
            .compile("\"detailInfo\":\\[.*,\"(已购房)\",.*]");

    private final static Pattern smokingInfo = Pattern
            .compile("\"detailInfo\":\\[.*,\"(不吸烟)\",.*]");

    private final static Pattern drinkingInfo = Pattern
            .compile("\"detailInfo\":\\[.*,\"(不喝酒)\",.*]");

    private final static Pattern childrenInfo = Pattern
            .compile("\"detailInfo\":\\[.*,\"([^\"]*有孩子[^\"]*)\",.*]");

    private final static Pattern pictureInfo = Pattern
            .compile("style=\"background-image:url\\(([^?]+)?");

    private final static Pattern linkInfo = Pattern.compile("href=\"([^\"]+)\">");

    private final static Parser parser = new ProfileParser();

    private ProfileParser(){}

    public static Parser getParser(){
        return parser;
    }

    @Override
    public ParseResult parse(String data) {
        Profile profile = new Profile();

        Matcher matcher = basicInfo.matcher(data);
        String basicData = "";
        if (matcher.find()) {
            basicData = matcher.group();
        }

        matcher = marriageInfo.matcher(basicData);
        if (matcher.find()) {
            profile.setMarriage(matcher.group(1));
        }

        matcher = ageInfo.matcher(basicData);
        if (matcher.find()) {
            profile.setAge(matcher.group(1));
        }

        matcher = constellationInfo.matcher(basicData);
        if (matcher.find()) {
            profile.setConstellation(matcher.group(1));
        }

        matcher = heightInfo.matcher(basicData);
        if (matcher.find()) {
            profile.setHeight(matcher.group(1));
        }

        matcher = weightInfo.matcher(basicData);
        if (matcher.find()) {
            profile.setWeight(matcher.group(1));
        }

        matcher = workPlaceIncomeInfo.matcher(basicData);
        if (matcher.find()) {
            profile.setWorkPlace(matcher.group(1));
            profile.setIncome(matcher.group(2));
        }

        matcher = workEducationInfo.matcher(basicData);
        if (matcher.find()) {
            profile.setOccupation(matcher.group(1));
            profile.setEducation(matcher.group(2));
        }

        matcher = coreInfo.matcher(data);
        if (matcher.find()) {
            profile.setGender(matcher.group(1));
            profile.setName(matcher.group(2));
        }

        matcher = detailInfo.matcher(data);
        String detailData = "";
        if (matcher.find()) {
            detailData = matcher.group();
        }

        matcher = resKidInfo.matcher(detailData);
        if (matcher.find()) {
            profile.setResidence(matcher.group(1));
            profile.setWantKids(matcher.group(2));
        }

        matcher = carInfo.matcher(detailData);
        if (matcher.find()) {
            profile.setCar(true);
        } else {
            profile.setCar(false);
        }

        matcher = houseInfo.matcher(detailData);
        if (matcher.find()) {
            profile.setHouse(true);
        } else {
            profile.setHouse(false);
        }

        matcher = smokingInfo.matcher(detailData);
        if (matcher.find()) {
            profile.setSmoking(false);
        } else {
            profile.setSmoking(true);
        }

        matcher = drinkingInfo.matcher(detailData);
        if (matcher.find()) {
            profile.setDrinking(false);
        } else {
            profile.setDrinking(true);
        }

        matcher = childrenInfo.matcher(detailData);
        if (matcher.find()) {
            profile.setChildren(true);
        } else {
            profile.setDrinking(false);
        }

        matcher = pictureInfo.matcher(data);
        if (matcher.find()) {
            profile.setPicture(matcher.group(1));
        }

        matcher = linkInfo.matcher(data);
        if (matcher.find()) {
            profile.setProfileLink(matcher.group(1).replace("m.", ""));
        }

        System.out.println("Received profile: " + profile.toString());
        return new ParseResult(new ArrayList<>(), new ArrayList<>());
    }
}