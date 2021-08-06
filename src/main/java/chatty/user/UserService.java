package chatty.user;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class UserService {

    @Inject
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public Optional<UserEntity> findByUserName(final String userName) {
        return userRepository.findByUserName(userName);
    }


    @Transactional
    public UserEntity createUser(final UserEntity entity) {
        return userRepository.persist(entity);
    }
}
