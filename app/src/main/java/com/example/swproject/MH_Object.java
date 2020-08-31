package com.example.swproject;

public class MH_Object {
    private String m_img_url;
    private String m_detail_link;



    public MH_Object(String url, String link){
        this.m_img_url = url;
        this.m_detail_link = link;

    }



    public String getM_Img_url() {
        return m_img_url;
    }

    public String getM_Detail_link() {
        return m_detail_link;
    }


}
