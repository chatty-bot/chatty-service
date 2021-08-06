package chatty.seq2seq;


import java.security.Principal;
import java.util.List;
import javax.inject.Inject;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/seq2seq")
@Secured(SecurityRule.IS_ANONYMOUS)
public class Seq2SeqController {

    @Inject
    private Seq2SeqService seq2SeqService;


    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public List<Seq2SeqTO> findAllSeq2SeqByUser(final Principal principal) {
        return seq2SeqService.getAllSeq2SeqForUser(principal.getName());

    }


    @Get("{seq2SeqId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Seq2SeqTO findSeq2SeqById(@PathVariable(name = "seq2SeqId") final String seq2SeqId) {
        return seq2SeqService.getSeq2SeqById(seq2SeqId);
    }
}
