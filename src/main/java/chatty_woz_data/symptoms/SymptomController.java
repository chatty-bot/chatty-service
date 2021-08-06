package chatty_woz_data.symptoms;


import java.util.List;
import javax.inject.Inject;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/symptoms")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class SymptomController {


    @Inject
    private SymptomService symptomService;


    @Get
    public List<SymptomTO> getAllSymptoms() {
        return symptomService.getAllSymptoms();
    }
}
