package com.coreintegra.pftrust.controllers.administration;

import com.coreintegra.pftrust.entities.administration.UserTenantMapping;
import com.coreintegra.pftrust.entities.tenant.dtos.UserTenant;
import com.coreintegra.pftrust.services.storage.FileStorageService;
import com.coreintegra.pftrust.services.tenant.TenantService;
import com.coreintegra.pftrust.util.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(AdministrationEndpoints.AUTH_SERVICE_ENDPOINT)
public class AuthController {

    private final TenantService tenantService;
    private final FileStorageService fileStorageService;

    public AuthController(TenantService tenantService, FileStorageService fileStorageService) {
        this.tenantService = tenantService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping(AdministrationEndpoints.TENANTS_ENDPOINT + "/{username}")
    public ResponseEntity<Object> getTenants(@PathVariable String username) throws Exception {

        List<UserTenantMapping> userTenantMappingList = tenantService.getTenantsByUserName(username);

        List<UserTenant> userTenants = userTenantMappingList.stream()
                .map(mapping -> new UserTenant(mapping.getTenant().getName(),
                        mapping.getTenant().getTenantId()))
                .collect(Collectors.toList());

        return Response.success(userTenants);
    }

    @GetMapping("/current-time")
    public ResponseEntity<Object> getCurrentTime(){

        Instant now = Instant.now();

        Map<String, Object> map = new HashMap<>();
        map.put("milliseconds", now.toEpochMilli());
        map.put("date", now);
        map.put("seconds", now.getEpochSecond());

        return Response.success(map);
    }

    @GetMapping("/downloadDocuments")
    public ResponseEntity<byte[]> downloadDocuments(@RequestParam String document) throws Exception {

        Path targetLocation = fileStorageService.resolvePath(document);

        byte[] bytes = Files.readAllBytes(targetLocation);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+document);
        headers.add("X-Suggested-Filename", document);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);

    }

}
