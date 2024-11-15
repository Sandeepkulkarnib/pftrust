package com.coreintegra.pftrust.dtos.pdf;

import java.util.Date;

public class TransferInReminders {

    private Long id;
    private Date createdAt;

    public TransferInReminders(Long id, Date createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
