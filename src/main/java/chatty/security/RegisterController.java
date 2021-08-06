package chatty.security;

import javax.inject.Inject;
import chatty.user.UserEntity;
import chatty.user.UserService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import static io.micronaut.security.rules.SecurityRule.IS_ANONYMOUS;

@Controller("/register")
@Secured(IS_ANONYMOUS)
public class RegisterController {


    @Inject
    private UserService userService;


    @Post
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity register(UserEntity entity) {
        return userService.createUser(entity);
    }


}
