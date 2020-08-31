package com.example.swproject;

public class MA_Object {
    private String m_img_url;
    private String m_name;



    public MA_Object(String url, String name){
        this.m_img_url = url;
        this.m_name = name;

    }



    public String getM_Img_url() {
        return m_img_url;
    }

    public String getM_Name() {
        return m_name;
    }


}
