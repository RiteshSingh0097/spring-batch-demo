package com.ritesh.springbatchdemo.batch;

import com.ritesh.springbatchdemo.model.PersonalDetails;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserPersonalProcessor implements ItemProcessor<PersonalDetails, PersonalDetails> {
    @Override
    public PersonalDetails process(PersonalDetails personalDetails) {
        personalDetails.setName("Mr. " + personalDetails.getName());
        return personalDetails;
    }
}
