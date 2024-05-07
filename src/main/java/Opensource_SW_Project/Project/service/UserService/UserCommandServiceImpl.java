package Opensource_SW_Project.Project.service.UserService;

import Opensource_SW_Project.Project.converter.UserConverter;
import Opensource_SW_Project.Project.domain.User;
import Opensource_SW_Project.Project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserCommandServiceImpl implements  UserCommandService{

    private final UserRepository userRepository;

    public User createUser(String name){
        User newUser = User.builder()
                .name(name)
                .build();
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }
}
