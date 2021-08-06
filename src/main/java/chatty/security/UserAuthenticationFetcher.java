package chatty.security;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.reactivestreams.Publisher;
import io.micronaut.security.authentication.providers.UserFetcher;
import io.micronaut.security.authentication.providers.UserState;
import io.reactivex.Flowable;
import chatty.user.UserEntity;
import chatty.user.UserRepository;

@Singleton
public class UserAuthenticationFetcher implements UserFetcher {

    @Inject
    private UserRepository userRepository;


    @Override
    public Publisher<UserState> findByUsername(final String username) {
        final Optional<UserEntity> userEntityOptional = userRepository.findByUserName(username);
        if (userEntityOptional.isPresent()) {
            return Flowable.just(userEntityOptional.get());
        }
        return Flowable.empty();
    }
}
