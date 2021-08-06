package chatty.security;

import javax.inject.Singleton;
import io.micronaut.security.authentication.providers.PasswordEncoder;

@Singleton
public class UserPasswordEncoder implements PasswordEncoder {


    @Override
    public String encode(final String rawPassword) {
        return rawPassword;
    }


    @Override
    public boolean matches(final String rawPassword, final String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
