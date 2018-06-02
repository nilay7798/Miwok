package com.example.npatel.myapplication;

public class word {
   private String defualtword,miwokword;
    private int imageresid=-1,audioresid;
    public word(String defaultword,String miwokword,int audioresid)
    {
        this.defualtword=defaultword;
        this.miwokword=miwokword;
        this.audioresid=audioresid;

    }
    public word(String defaultword,String miwokword,int imageresid,int audioresid)
    {
        this.defualtword=defaultword;
        this.miwokword=miwokword;
        this.imageresid=imageresid;
        this.audioresid=audioresid;
    }

    public String getDefualtword()
    {
        return defualtword;
    }
    public String getMiwokword()
    {
        return miwokword;
    }
    public int getImageresid()
    {
        return imageresid;
    }
    public boolean hasimage()
    {
        return imageresid!=-1;
    }
    public int getAudioresid(){return audioresid;}
}
