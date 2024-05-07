package Opensource_SW_Project.Project.service.UserService;

import Opensource_SW_Project.Project.domain.User;
import Opensource_SW_Project.Project.web.dto.UserRequestDTO;

public interface UserCommandService {
    User createUser(String name);
}
