package com.example.lab06service.controller;

import com.example.lab06service.pojo.Wizard;
import com.example.lab06service.repo.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WizardController {
    @Autowired
    WizardService wizService;
    Wizard newWizard;

    public WizardController(){}

    @GetMapping("/wizards")
    public ResponseEntity<?> getWiz(){
        List<Wizard> wiz = wizService.getWiz();
        System.out.println("Successfully Get All Wizards");
        return ResponseEntity.ok(wiz);
    }

    @PostMapping("/addWizard")
    public ResponseEntity<Wizard> addWiz(@RequestBody Wizard wizzy){
        Wizard wiz = wizService.addWiz(wizzy);
        System.out.println("Successfully Add New Wizard, " + wizzy.getName());
        return ResponseEntity.ok(wiz);
    }

    @PostMapping("/updateWizard")
    public ResponseEntity<Wizard> updateWiz(@RequestBody Wizard wizzy){
        Wizard wiz = wizService.updateWiz(wizzy);
        System.out.println("Successfully Edit Wizard, " + wizzy.getName());
        return ResponseEntity.ok(wiz);
    }

    @PostMapping("/deleteWizard")
    public ResponseEntity<Boolean> delWiz(@RequestBody Wizard wizzy){
        boolean wiz = wizService.delWiz(wizzy);
        System.out.println("Successfully Delete Wizard, " + wizzy.getName());
        return ResponseEntity.ok(wiz);
    }
}
