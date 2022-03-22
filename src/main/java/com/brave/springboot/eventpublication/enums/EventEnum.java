package com.brave.springboot.eventpublication.enums;

/**
 * @author jbrave
 */
public enum EventEnum {

    TEST("test", "事件A");

    private String operator;

    private String desc;

    EventEnum(String operator, String desc) {
        this.operator = operator;
        this.desc = desc;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
