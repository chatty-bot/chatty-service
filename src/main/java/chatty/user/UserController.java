package chatty.user;

import java.security.Principal;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/user")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController {

    @Get
    public String getUserName(Principal principal) {
        return principal.getName();
    }
}
