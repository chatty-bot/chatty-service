package chatty_woz_data.illnesses;

import java.util.List;
import javax.inject.Inject;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/illnesses")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class IllnessController {


    @Inject
    private IllnessService illnessService;


    @Get
    public List<IllnessTO> getAllIllnesses() {
        return illnessService.getAll();
    }
}
