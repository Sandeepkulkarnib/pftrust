package com.coreintegra.pftrust.dtos;

import org.springframework.web.multipart.MultipartFile;

public class FormDTO {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
