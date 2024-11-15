package com.coreintegra.pftrust.controllers.pf.files;

import com.coreintegra.pftrust.dtos.FormDTO;
import com.coreintegra.pftrust.util.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(FileServiceEndpoints.FILE_SERVICE_ENDPOINT)
public class FileServiceController {

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> upload(HttpServletRequest request){

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        MultipartHttpServletRequest multipartHttpServletRequest = commonsMultipartResolver.resolveMultipart(request);

        System.out.println(multipartHttpServletRequest.getFile("file").getOriginalFilename());

        return Response.success("File uploaded successfully.");

    }



}
