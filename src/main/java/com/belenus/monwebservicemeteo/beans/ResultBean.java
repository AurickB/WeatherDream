package com.belenus.monwebservicemeteo.beans;

import java.util.ArrayList;

public class ResultBean {
    private ErrorBean errors;
    private ArrayList<WeatherBean> weather;
    private MainBean main;
    private WindBean wind;
    private SysBean sys;
    private String name;

    public ErrorBean getErrors() {
        return errors;
    }

    public void setErrors(ErrorBean errors) {
        this.errors = errors;
    }

    public ArrayList<WeatherBean> getResults() {
        return weather;
    }

    public void setResults(ArrayList<WeatherBean> results) {
        weather = results;
    }

    public MainBean getMainResults() {
        return main;
    }

    public void setMainResults(MainBean mainResults) {
        main = mainResults;
    }

    public WindBean getWindResults() {
        return wind;
    }

    public void setWindResults(WindBean windResults) {
        wind = windResults;
    }

    public SysBean getSysResults() {
        return sys;
    }

    public void setSysResults(SysBean sysResults) {
        sys = sysResults;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
