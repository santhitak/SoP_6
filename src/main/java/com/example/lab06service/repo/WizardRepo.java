package com.example.lab06service.repo;

import com.example.lab06service.pojo.Wizard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WizardRepo extends MongoRepository<Wizard, String> {

}
