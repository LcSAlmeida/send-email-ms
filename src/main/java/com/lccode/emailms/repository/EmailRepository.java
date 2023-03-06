package com.lccode.emailms.repository;

import com.lccode.emailms.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailEntity, UUID> {
}
