package com.coreintegra.pftrust.services.tenant;

import com.coreintegra.pftrust.entities.administration.User;
import com.coreintegra.pftrust.entities.administration.UserTenantMapping;
import com.coreintegra.pftrust.entities.tenant.NotificationEmailDesign;
import com.coreintegra.pftrust.entities.tenant.PdfDocumentDesign;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.entities.tenant.TenantImage;
import com.coreintegra.pftrust.repositories.administration.UserRepository;
import com.coreintegra.pftrust.repositories.pf.NotificationEmailDesignRepository;
import com.coreintegra.pftrust.repositories.pf.PdfDocumentDesignRepository;
import com.coreintegra.pftrust.repositories.tenant.TenantImageRepository;
import com.coreintegra.pftrust.repositories.tenant.TenantRepository;
import com.coreintegra.pftrust.repositories.tenant.UserTenantMappingRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {

    private final UserTenantMappingRepository userTenantMappingRepository;
    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final TenantImageRepository tenantImageRepository;
    private final NotificationEmailDesignRepository notificationEmailDesignRepository;
    private final PdfDocumentDesignRepository pdfDocumentDesignRepository;

    public TenantServiceImpl(UserTenantMappingRepository userTenantMappingRepository,
                             UserRepository userRepository, TenantRepository tenantRepository,
                             TenantImageRepository tenantImageRepository,
                             NotificationEmailDesignRepository notificationEmailDesignRepository,
                             PdfDocumentDesignRepository pdfDocumentDesignRepository) {
        this.userTenantMappingRepository = userTenantMappingRepository;
        this.userRepository = userRepository;
        this.tenantRepository = tenantRepository;
        this.tenantImageRepository = tenantImageRepository;
        this.notificationEmailDesignRepository = notificationEmailDesignRepository;
        this.pdfDocumentDesignRepository = pdfDocumentDesignRepository;
    }

    @Override
    public List<UserTenantMapping> getTenantsByUserName(String username) throws Exception {

        Optional<User> optionalUser = userRepository.findByUsernameAndIsActive(username, true);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User does not exists");
        }

        List<UserTenantMapping> userTenantMappingList = userTenantMappingRepository
                .getUserTenantMappingByUserAndIsActive(optionalUser.get(), true);

        if(userTenantMappingList.isEmpty()){
            throw new Exception("tenants not assigned to user");
        }

        return userTenantMappingList;

    }

    @Override
    public Tenant getTenantForUser(String tenantId, User user) throws Exception {

        Tenant tenant = getTenantByTenantId(tenantId);

        List<UserTenantMapping> userTenantMappingList = getUserTenantMappings(user, tenant);

        if(userTenantMappingList.isEmpty()){
            throw new Exception("Tenant not assigned to user");
        }

        return tenant;

    }

    @Override
    public List<UserTenantMapping> getUserTenantMappings(User user, Tenant tenant) {
        return userTenantMappingRepository.getUserTenantMappingByUserAndTenantAndIsActive(user,
                tenant, true);
    }

    @Override
    public Tenant getTenantByTenantId(String tenantId) {

        Optional<Tenant> optionalTenant = tenantRepository
                .findByTenantIdAndIsActive(tenantId, true);

        if(optionalTenant.isEmpty()){
            throw new EntityNotFoundException("Tenant not found");
        }

        return optionalTenant.get();
    }

    @Override
    public void updateTenant(Tenant tenant) {
        tenantRepository.save(tenant);
    }

    @Override
    public List<TenantImage> getImages(Tenant tenant) {
        return tenantImageRepository.getAllByTenant(tenant);
    }

    @Override
    public void saveTenantImage(TenantImage tenantImage) {
        tenantImageRepository.save(tenantImage);
    }

    @Override
    public void uploadEmailTemplate(String template, String type) {

        Optional<NotificationEmailDesign> optional = notificationEmailDesignRepository.findByDocumentType(type);

        NotificationEmailDesign notificationEmailDesign = optional.orElse(new NotificationEmailDesign());

        notificationEmailDesign.setDocument(template);
        notificationEmailDesign.setDocumentType(type);

        notificationEmailDesignRepository.save(notificationEmailDesign);

    }

    @Override
    public void uploadPDFTemplate(String template, String type) {

        Optional<PdfDocumentDesign> optional = pdfDocumentDesignRepository.findByDocumentType(type);

        PdfDocumentDesign pdfDocumentDesign = optional.orElse(new PdfDocumentDesign());

        pdfDocumentDesign.setDocument(template);
        pdfDocumentDesign.setDocumentType(type);

        pdfDocumentDesignRepository.save(pdfDocumentDesign);

    }

    @Override
    public List<NotificationEmailDesign> getEmailDesigns() {
        return notificationEmailDesignRepository.findAllByIsActive(true);
    }

    @Override
    public List<PdfDocumentDesign> getPdfDocumentDesigns() {
        return pdfDocumentDesignRepository.findAllByIsActive(true);
    }

}
