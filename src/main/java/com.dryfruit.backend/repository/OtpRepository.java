package com.dryfruit.backend.repository;

import com.dryfruit.backend.model.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtpRepository extends MongoRepository<Otp, String> {
    Otp findByEmail(String email);
}
