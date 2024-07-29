package com.BGL.test.component;

import com.BGL.test.entity.Entry;
import com.BGL.test.service.EntryService;
import com.BGL.test.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntryServiceFactory {
    @Autowired
    private BasicBankEntryService basicBankEntryService;
    @Autowired
    private ContributionService contributionService;
    @Autowired
    private DistributionInterestService distributionInterestService;
    @Autowired
    private DividendService dividendService;
    @Autowired
    private InvestmentService investmentService;
    public EntryService<? extends Entry> getService(String entryType){
        switch (entryType.toLowerCase().trim()){
            case "basicbankentry":
                return basicBankEntryService;
            case "contribution":
                return contributionService;
            case "distributioninterest":
                return distributionInterestService;
            case "dividend":
                return dividendService;
            case "investment":
                return investmentService;
            default:
                throw new IllegalArgumentException("Unknown Entry type: " + entryType);
        }
    }
}
