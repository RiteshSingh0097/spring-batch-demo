package com.ritesh.springbatchdemo.batch;

import com.ritesh.springbatchdemo.model.PersonalDetails;
import com.ritesh.springbatchdemo.repository.UserPersonalRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBPersonalWriter implements ItemWriter<PersonalDetails> {

    @Autowired
    UserPersonalRepository personalRepository;

    @Override
    public void write(List<? extends PersonalDetails> personalDetails) {
        personalRepository.saveAll(personalDetails);
    }
}
