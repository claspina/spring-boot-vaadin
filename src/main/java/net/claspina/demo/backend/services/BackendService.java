package net.claspina.demo.backend.services;

//import org.springframework.security.access.annotation.Secured;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BackendService {

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public String adminMethod() {
        return "Hello from an admin method";
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','BASICO')")
    public String userMethod() {
        return "Hello from a user method";
    }
}
