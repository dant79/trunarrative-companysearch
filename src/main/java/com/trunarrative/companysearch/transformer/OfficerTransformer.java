package com.trunarrative.companysearch.transformer;

import com.trunarrative.companysearch.client.officersearch.OfficerResult;
import com.trunarrative.companysearch.model.Officer;
import org.springframework.stereotype.Component;

@Component
public class OfficerTransformer {

    public Officer map(OfficerResult result) {
        return new Officer()
                .setAddress(result.getAddress())
                .setName(result.getName())
                .setOfficerRole(result.getOfficerRole())
                .setAppointedOn(result.getAppointedOn());
    }
}
