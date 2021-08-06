package chatty.dst.boundary;

import java.security.Principal;
import java.util.List;
import javax.inject.Inject;
import chatty.dst.types.DstTO;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/dst")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class DstController {


    @Inject
    private DstFacade dstFacade;


    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public DstTO create(final Principal principal, final DstTO newDst) {
        return dstFacade.createNewDst(principal.getName(), newDst);
    }


    @Get(value = "/{dstName}", produces = MediaType.APPLICATION_JSON)
    public DstTO get(final Principal principal, final String dstName) {
        return dstFacade.findDstByName(principal.getName(), dstName);
    }


    @Get
    public List<DstTO> findAll(final Principal principal) {
        return dstFacade.findAllForUser(principal.getName());

    }


    @Put
    public DstTO update(final Principal principal, final DstTO updatedDst) {
        return dstFacade.updateDst(principal.getName(), updatedDst);
    }
}
