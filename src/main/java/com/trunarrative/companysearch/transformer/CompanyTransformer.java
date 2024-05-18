package com.trunarrative.companysearch.transformer;

import com.trunarrative.companysearch.client.companysearch.CompanyResult;
import com.trunarrative.companysearch.model.Company;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class CompanyTransformer {

    public Company map(CompanyResult result) {
        return new Company()
                .setCompanyNumber(result.getCompanyNumber())
                .setCompanyStatus(result.getCompanyStatus())
                .setCompanyType(result.getCompanyType())
                .setAddress(result.getAddress())
                .setDateOfCreation(result.getDateOfCreation())
                .setTitle(result.getTitle());
    }
}
