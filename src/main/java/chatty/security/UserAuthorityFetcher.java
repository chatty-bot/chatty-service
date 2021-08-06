package chatty.security;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import org.reactivestreams.Publisher;
import io.micronaut.security.authentication.providers.AuthoritiesFetcher;
import io.reactivex.Flowable;

@Singleton
public class UserAuthorityFetcher implements AuthoritiesFetcher {
    @Override
    public Publisher<List<String>> findAuthoritiesByUsername(final String username) {
        return Flowable.just(new ArrayList<>());
    }
}
