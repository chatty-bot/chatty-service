package chatty_woz_data.illnesses;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class IllnessService {

    @Inject
    private IllnessRepository illnessRepository;


    @Inject
    private IllnessMapper illnessMapper;


    @Transactional
    public List<IllnessTO> getAll() {
        return illnessRepository.findAll().stream().map(illnessMapper::toTO).collect(Collectors.toList());

    }
}
