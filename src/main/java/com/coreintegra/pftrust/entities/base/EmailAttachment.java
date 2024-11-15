package com.coreintegra.pftrust.entities.base;

import javax.mail.util.ByteArrayDataSource;

public class EmailAttachment {

    private String name;
    private ByteArrayDataSource inputStreamResource;

    public EmailAttachment(String name, ByteArrayDataSource inputStreamResource) {
        this.name = name;
        this.inputStreamResource = inputStreamResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ByteArrayDataSource getInputStreamResource() {
        return inputStreamResource;
    }

    public void setInputStreamResource(ByteArrayDataSource inputStreamResource) {
        this.inputStreamResource = inputStreamResource;
    }
}
