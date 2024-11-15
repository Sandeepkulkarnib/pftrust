package com.coreintegra.pftrust.services.tenant;

import com.coreintegra.pftrust.entities.administration.User;
import com.coreintegra.pftrust.entities.administration.UserTenantMapping;
import com.coreintegra.pftrust.entities.tenant.NotificationEmailDesign;
import com.coreintegra.pftrust.entities.tenant.PdfDocumentDesign;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.entities.tenant.TenantImage;

import java.util.List;

public interface TenantService {

    List<UserTenantMapping> getTenantsByUserName(String username) throws Exception;

    Tenant getTenantForUser(String tenantId, User user) throws Exception;

    List<UserTenantMapping> getUserTenantMappings(User user, Tenant tenant);

    Tenant getTenantByTenantId(String tenantId);

    void updateTenant(Tenant tenant);

    List<TenantImage> getImages(Tenant tenant);

    void saveTenantImage(TenantImage tenantImage);

    void uploadEmailTemplate(String template, String type);

    void uploadPDFTemplate(String template, String type);

    List<NotificationEmailDesign> getEmailDesigns();

    List<PdfDocumentDesign> getPdfDocumentDesigns();

}
