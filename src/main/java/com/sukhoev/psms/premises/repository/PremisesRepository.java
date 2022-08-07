package com.sukhoev.psms.premises.repository;

import com.sukhoev.psms.premises.entity.Premises;
import com.sukhoev.psms.registration.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremisesRepository extends JpaRepository<Premises, Long> {

}
