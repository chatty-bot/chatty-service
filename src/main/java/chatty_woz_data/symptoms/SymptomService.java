package chatty_woz_data.symptoms;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.micronaut.runtime.http.scope.RequestScope;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class SymptomService {

    @Inject
    private SymptomRepository symptomRepository;

    @Inject
    private SymptomMapper symptomMapper;


    @Transactional
    public List<SymptomTO> getAllSymptoms() {
        return symptomRepository.findAll().stream().map(symptomMapper::toTO).collect(Collectors.toList());
    }
}
