package com.mail.repository;

import com.mail.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
    Email findAllByIdMail(Long idMail);
}
