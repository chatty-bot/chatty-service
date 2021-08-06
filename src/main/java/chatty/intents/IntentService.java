package chatty.intents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.classifier.types.ClassifierEntity;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton

public class IntentService {


    @Inject
    private IntentRepository intentRepository;

    @Inject
    private IntentMapper intentMapper;


    @Transactional(readOnly = true)
    public Optional<IntentEntity> findIntentByNameAndClassifierId(final String intentName, final int classifierId) {
        return intentRepository.findByNameAndClassifierId(intentName, classifierId);
    }


    @Transactional(readOnly = true)
    public List<IntentTO> findIntentsByClassifierId(final int classifierId) {
        return intentRepository.findByClassifierId(classifierId).stream().map(intentMapper::toTO).collect(
                Collectors.toList());
    }


    @Transactional
    public IntentTO createIntent(final IntentTO intentTO) {
        final IntentEntity intentEntity = intentMapper.toBE(intentTO);
        return intentMapper.toTO(intentRepository.persist(intentEntity));
    }


    @Transactional
    public List<IntentTO> createIntentsOfStrings(List<String> intents, final ClassifierEntity classifierEntity) {

        List<IntentEntity> persistedEntities = new ArrayList<>();
        intents.forEach(intentName -> {
            if (!exists(intentName, classifierEntity.getId())) {
                final IntentEntity intentEntity = new IntentEntity();
                intentEntity.setIntentName(intentName);
                intentEntity.setClassifierEntity(classifierEntity);
                intentEntity.setNumberOfSamples(0);
                intentRepository.persist(intentEntity);
                persistedEntities.add(intentEntity);
            }
        });
        return persistedEntities.stream().map(intentMapper::toTO).collect(Collectors.toList());
    }


    @Transactional
    public List<IntentEntity> createIntents(final List<IntentEntity> intents, final ClassifierEntity classifierEntity) {
        intents.forEach(intentEntity -> {
            if (!exists(intentEntity, classifierEntity.getId())) {
                intentEntity.setClassifierEntity(classifierEntity);
                intentRepository.persist(intentEntity);
            }
        });

        return intents;
    }


    @Transactional(readOnly = true)
    public boolean exists(final IntentEntity intentEntity, final int classifierId) {
        return intentRepository.findByNameAndClassifierId(intentEntity.getIntentName(), classifierId).isPresent();
    }


    @Transactional(readOnly = true)
    public boolean exists(final String intentName, final Integer classifierId) {
        return intentRepository.findByNameAndClassifierId(intentName, classifierId).isPresent();
    }


    @Transactional(readOnly = true)
    public List<IntentTO> findIntentsByClassifierName(final ClassifierEntity classifierEntity) {

        return findIntentsByClassifierId(classifierEntity.getId());
    }
}
