package entity;

import java.io.Serializable;

/**
 * (CfgAct112)实体类
 *
 * @author makejava
 * @since 2022-04-19 21:22:33
 */
public class CfgAct112 implements Serializable {
    private static final long serialVersionUID = -86410067670907462L;
    
    private Integer mainlv;
    
    private String items;


    public Integer getMainlv() {
        return mainlv;
    }

    public void setMainlv(Integer mainlv) {
        this.mainlv = mainlv;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

}

