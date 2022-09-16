package com.example.lab06service.repo;

import com.example.lab06service.pojo.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WizardService {
    @Autowired
    private WizardRepo repo;

    public WizardService() {}

    public WizardService(WizardRepo repo){
        this.repo = repo;
    }

    public List<Wizard> getWiz(){
        return repo.findAll();
    }

    public Wizard addWiz(Wizard wiz){
        return repo.save(wiz);
    }

    public Wizard updateWiz(Wizard wiz){
        return repo.save(wiz);
    }

    public boolean delWiz(Wizard wiz){
        try{
            repo.delete(wiz);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
