package com.coremaker.coding.repository;

import com.coremaker.coding.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserData, Long> {

    UserData findByEmail(String email);

}
