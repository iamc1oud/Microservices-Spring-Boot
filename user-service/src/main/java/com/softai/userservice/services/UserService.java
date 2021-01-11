package com.softai.userservice.services;

import com.softai.userservice.VO.Department;
import com.softai.userservice.VO.ResponseTemplateVO;
import com.softai.userservice.entity.User;
import com.softai.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Insider getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Optional<User> user = userRepository.findById(userId);
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/" + user.get().getDepartmentId(), Department.class);
        vo.setUser(user.get());
        vo.setDepartment(department);
        return vo;
    }
}
