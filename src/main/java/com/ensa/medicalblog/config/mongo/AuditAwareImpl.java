package com.ensa.medicalblog.config.mongo;

import com.ensa.medicalblog.entity.UserEntity;
import com.ensa.medicalblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditAwareImpl implements AuditorAware<UserEntity> {
    private final UserRepository userRepository;


    @Override
    public Optional<UserEntity> getCurrentAuditor() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(userEntity);
    }
}
