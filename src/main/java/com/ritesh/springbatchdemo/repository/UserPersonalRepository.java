package com.ritesh.springbatchdemo.repository;

import com.ritesh.springbatchdemo.model.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersonalRepository extends JpaRepository<PersonalDetails, Integer> {
}
