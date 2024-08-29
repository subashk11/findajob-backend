package com.subash.projects.findajob.controller;

import com.subash.projects.findajob.entity.Company;
import com.subash.projects.findajob.service.company.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCompanies(){
        List<Company> companyList = companyService.getAllCompanies();

        if(!companyList.isEmpty()){
            return new ResponseEntity<>(companyList, HttpStatus.OK);
        }

        return new ResponseEntity<>("No Companies Currently!", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        boolean isCompanyCreated = companyService.createCompany(company);
        if(isCompanyCreated){
            return new ResponseEntity<>("Company created Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error while creating company!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable("id") String companyId){
        Optional<Company> company = companyService.getCompanyById(companyId);
        if(company.isPresent()){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable("id") String companyId){
        boolean isDeleted = companyService.deleteCompany(companyId);

        if(isDeleted)
            return new ResponseEntity<>("Deleted Successfully!", HttpStatus.OK);
        return new ResponseEntity<>("Error while deleting company!", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyDetails(@PathVariable("id") String companyId, @RequestBody Company company){
        boolean isUpdated = companyService.updateCompanyDetails(companyId, company);

        if(isUpdated){
            return new ResponseEntity<>("Successfully updated details!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error while updating company details!", HttpStatus.NOT_FOUND);
    }
}
