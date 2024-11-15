package com.coreintegra.pftrust.controllers.administration;

import com.coreintegra.pftrust.entities.RequestLogger;
import com.coreintegra.pftrust.repositories.RequestLoggerRepository;
import com.coreintegra.pftrust.util.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.coreintegra.pftrust.controllers.administration.AdministrationEndpoints.ACTIVITY_LOG_SERVICE_ENDPOINT;

@RestController
@RequestMapping(ACTIVITY_LOG_SERVICE_ENDPOINT)
public class UserActivityController {

    private final RequestLoggerRepository requestLoggerRepository;

    public UserActivityController(RequestLoggerRepository requestLoggerRepository) {
        this.requestLoggerRepository = requestLoggerRepository;
    }

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam(value = "search", required = false, defaultValue = "")String search,
                                      @RequestParam(value = "page", required = false, defaultValue = "1")Integer page,
                                      @RequestParam(value = "size", required = false, defaultValue = "10")Integer size){

        Pageable pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        Specification<RequestLogger> requestLoggerSpecification = usernameSpecification(search);

        Page<RequestLogger> requestLoggerPage = requestLoggerRepository.findAll(requestLoggerSpecification, pageable);

        return Response.success(requestLoggerPage);
    }

    private Specification<RequestLogger> usernameSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + search + "%");
    }

}
