package com.coreintegra.pftrust.entities.pf.transferIn.dtos;

public class TransferInDocumentDetailsDTO {

    private String name;
    private String path;

    public TransferInDocumentDetailsDTO(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
