package com.coreintegra.pftrust.dtos.pdf;

import com.coreintegra.pftrust.util.NumberFormatter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nominee")
public class Nominee {

    private String name;
    private String relationship;
    private Float share;

    public Nominee() {
    }

    public Nominee(String name, String relationship, Float share) {
        this.name = name;
        this.relationship = relationship;
        this.share = share;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship == null ? "" : relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Float getShare() {
        return share == null ? 0f : share;
    }

    @XmlElement(name = "sharefr")
    public String getSh(){
        return NumberFormatter.formatNumbers(share);
    }

    public void setShare(Float share) {
        this.share = share;
    }
}
