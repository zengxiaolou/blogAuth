package com.ruler.auth.config;

import com.ruler.auth.entity.Role;
import com.ruler.auth.infrastructure.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class RoleInitializationService {
    private final RoleRepository roleRepository;

    public RoleInitializationService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initRoles() {
        if (roleRepository.findByName("ADMIN") == null) {
            roleRepository.save(new Role("ADMIN", "管理员"));
        }
        if (roleRepository.findByName("USER") == null) {
            roleRepository.save(new Role("USER", "用户"));
        }
    }

}
