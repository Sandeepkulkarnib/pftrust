package com.coreintegra.pftrust.controllers.administration.tenant;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.tenant.NotificationEmailDesign;
import com.coreintegra.pftrust.entities.tenant.PdfDocumentDesign;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.entities.tenant.TenantImage;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.services.storage.FileStorageService;
import com.coreintegra.pftrust.services.tenant.TenantService;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.Response;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(TenantServiceEndpoints.TENANT_SERVICE_ENDPOINT)
public class TenantServiceController {

    public final TenantService tenantService;
    private final FileStorageService fileStorageService;

    public TenantServiceController(TenantService tenantService, FileStorageService fileStorageService) {
        this.tenantService = tenantService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/details")
    public ResponseEntity<Object> details(){

        Tenant currentTenant = TenantContext.getCurrentTenant();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", currentTenant.getEntityId());
        jsonObject.put("name", currentTenant.getName());
        jsonObject.put("code", currentTenant.getCode());
        jsonObject.put("description", currentTenant.getDescription());
        jsonObject.put("theme", currentTenant.getTheme());
        jsonObject.put("clock", currentTenant.getClock());
        jsonObject.put("height", currentTenant.getHeight());
        jsonObject.put("width", currentTenant.getWidth());
        jsonObject.put("logo", currentTenant.getLogoImage());
        jsonObject.put("styleClasses", currentTenant.getStyleClasses());

        return Response.success(jsonObject.toString());

    }

    @PutMapping("/uploadLogoImage")
    public ResponseEntity<Object> uploadLogoImage(@RequestBody String request){

        Tenant currentTenant = TenantContext.getCurrentTenant();

        JSONObject jsonObject = new JSONObject(request);

        String logoImage = jsonObject.getString("logoImage");
        String logoWidth = jsonObject.getString("logoWidth");
        String logoHeight = jsonObject.getString("logoHeight");

        if(logoImage != null && !logoImage.isEmpty() && !logoImage.isBlank()){
            currentTenant.setLogoImage(logoImage);
        }

        if(logoWidth != null && !logoWidth.isEmpty() && !logoWidth.isBlank()){
            currentTenant.setWidth(logoWidth);
        }

        if(logoHeight != null && !logoHeight.isEmpty() && !logoHeight.isBlank()){
            currentTenant.setHeight(logoHeight);
        }

        tenantService.updateTenant(currentTenant);

        JSONObject response = new JSONObject();
        response.put("logoImage", currentTenant.getLogoImage());
        response.put("logoWidth", currentTenant.getWidth());
        response.put("logoHeight", currentTenant.getHeight());

        return Response.success(response.toString());

    }

    @PutMapping("/updateColorTheme")
    public ResponseEntity<Object> updateColorTheme(@RequestBody String request){

        Tenant currentTenant = TenantContext.getCurrentTenant();

        JSONObject jsonObject = new JSONObject(request);

        String colorTheme = jsonObject.getString("colorTheme");

        if(colorTheme == null || colorTheme.isEmpty() || colorTheme.isBlank()){
            return Response.failure("Please provide valid color theme");
        }

        currentTenant.setTheme(colorTheme);

        tenantService.updateTenant(currentTenant);

        JSONObject response = new JSONObject();
        response.put("colorTheme", currentTenant.getTheme());

        return Response.success(response.toString());

    }

    @GetMapping("/images")
    @ApplyTenantFilter
    public ResponseEntity<Object> getTenantImages(){

        Tenant currentTenant = TenantContext.getCurrentTenant();
        List<TenantImage> tenantImages = tenantService.getImages(currentTenant);

        JSONArray jsonArray = new JSONArray();

        tenantImages.forEach(tenantImage -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", tenantImage.getEntityId());
            jsonObject.put("path", tenantImage.getPath());
            jsonArray.put(jsonObject);
        });

        return Response.success(jsonArray.toString());

    }

    @PostMapping(value = "/upload",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApplyTenantFilter
    public ResponseEntity<Object> upload(HttpServletRequest httpServletRequest){

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

        MultipartHttpServletRequest request = commonsMultipartResolver.resolveMultipart(httpServletRequest);

        Iterator<String> itr =  request.getFileNames();

        JSONObject jsonObject = new JSONObject();

        while (itr.hasNext()){

            String next = itr.next();

            MultipartFile file = request.getFile(next);

            String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            String tenantCode = TenantContext.getCurrentTenant().getCode().toLowerCase();
            String fileName = tenantCode + "_" + UUID.randomUUID() + "_" + "." + extension;

            String storeFile = fileStorageService.storeFile(file, fileName, tenantCode);

            TenantImage tenantImage = new TenantImage();
            tenantImage.setPath(storeFile);

            tenantService.saveTenantImage(tenantImage);

            jsonObject.put("path", storeFile);

        }

        return Response.success(jsonObject.toString());

    }

    @PostMapping("/uploadEmailTemplate")
    @ApplyTenantFilter
    public ResponseEntity<Object> uploadEmailTemplate(@RequestBody String request){

        JSONObject jsonObject = new JSONObject(request);

        String template = jsonObject.getString("template");
        String type = jsonObject.getString("type");

        boolean isValid = false;

        for (String supportedTemplate:NotificationEmailDesign.supportedTemplates) {
            if(supportedTemplate.equalsIgnoreCase(type)){
                isValid = true;
                break;
            }
        }

        if(!isValid){
            return Response.failure("Invalid Template Type.");
        }

        tenantService.uploadEmailTemplate(template, type.toUpperCase());

        JSONObject response = new JSONObject();
        response.put("message", "done");

        return Response.success(response.toString());

    }

    @PostMapping("/uploadPdfTemplate")
    @ApplyTenantFilter
    public ResponseEntity<Object> uploadPdfTemplate(@RequestBody String request){

        JSONObject jsonObject = new JSONObject(request);

        String template = jsonObject.getString("template");
        String type = jsonObject.getString("type");

        boolean isValid = false;

        for (String supportedTemplate:PdfDocumentDesign.supportedTemplates) {
            if(supportedTemplate.equalsIgnoreCase(type)){
                isValid = true;
                break;
            }
        }

        if(!isValid){
            return Response.failure("Invalid Template Type.");
        }

        tenantService.uploadPDFTemplate(template, type);

        JSONObject response = new JSONObject();
        response.put("message", "done");

        return Response.success(response.toString());

    }

    @GetMapping("/emailTemplates")
    @ApplyTenantFilter
    public ResponseEntity<Object> emailTemplates() {

        List<NotificationEmailDesign> emailDesigns = tenantService.getEmailDesigns();

        List<String> supportedTemplates = NotificationEmailDesign.supportedTemplates;

        JSONArray emailDesignsFormatted = new JSONArray();

        for (NotificationEmailDesign emailDesign:emailDesigns) {

            JSONObject design = new JSONObject();

            design.put("id", emailDesign.getEntityId());
            design.put("name", emailDesign.getDocumentType());
            design.put("createdAt", DateFormatterUtil.formatDateR(emailDesign.getCreatedAt()));
            design.put("updatedAt", DateFormatterUtil.formatDateR(emailDesign.getUpdatedAt()));
            design.put("createdBy", emailDesign.getCreatedByUser() == null ? "" : emailDesign.getCreatedByUser().getUsername());
            design.put("updatedBy", emailDesign.getLastModifiedByUser() == null ? "" : emailDesign.getLastModifiedByUser().getUsername());

            emailDesignsFormatted.put(design);

        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("emailDesigns", emailDesignsFormatted);
        jsonObject.put("supportedTemplates", supportedTemplates);

        return Response.success(jsonObject.toString());

    }

    @GetMapping("/pdfTemplates")
    @ApplyTenantFilter
    public ResponseEntity<Object> pdfTemplates() {

        List<PdfDocumentDesign> pdfDocumentDesigns = tenantService.getPdfDocumentDesigns();

        List<String> supportedTemplates = PdfDocumentDesign.supportedTemplates;

        JSONArray pdfDesignsFormatted = new JSONArray();

        for (PdfDocumentDesign pdfDocumentDesign:pdfDocumentDesigns) {

            JSONObject design = new JSONObject();

            design.put("id", pdfDocumentDesign.getEntityId());
            design.put("name", pdfDocumentDesign.getDocumentType());
            design.put("createdAt", DateFormatterUtil.formatDateR(pdfDocumentDesign.getCreatedAt()));
            design.put("updatedAt", DateFormatterUtil.formatDateR(pdfDocumentDesign.getUpdatedAt()));
            design.put("createdBy", pdfDocumentDesign.getCreatedByUser() == null ? "" : pdfDocumentDesign.getCreatedByUser().getUsername());
            design.put("updatedBy", pdfDocumentDesign.getLastModifiedByUser() == null ? "" : pdfDocumentDesign.getLastModifiedByUser().getUsername());

            pdfDesignsFormatted.put(design);

        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pdfDesigns", pdfDesignsFormatted);
        jsonObject.put("supportedTemplates", supportedTemplates);

        return Response.success(jsonObject.toString());

    }


}
