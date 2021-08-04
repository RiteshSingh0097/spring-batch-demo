package com.ritesh.springbatchdemo.batch;

import com.ritesh.springbatchdemo.model.User;
import com.ritesh.springbatchdemo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DBWriter implements ItemWriter<User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void write(List<? extends User> users) {
        log.info("data saved in database :: " + users);
        userRepository.saveAll(users);
    }
}
