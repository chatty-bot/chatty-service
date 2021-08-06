package chatty.answer_selection;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.answer_selection.types.AnswerSelectionEntity;
import chatty.answer_selection.types.AnswerSelectionTO;
import chatty.classifier.control.ClassifierService;
import chatty.classifier.types.ClassifierTO;
import chatty.user.UserEntity;
import chatty.user.UserService;
import chatty.util.exceptions.UserNotFoundException;
import io.micronaut.spring.tx.annotation.Transactional;

/**
 * I'm the service that controls ligc regading answer selection modules
 */
@Singleton
public class AnswerSelectionService {

    @Inject
    private AnswerSelectionRepository answerSelectionRepository;

    @Inject
    private UserService userService;

    @Inject
    private AnswerSelectionMapper answerSelectionMapper;

    @Inject
    private ClassifierService classifierService;


    @Transactional(readOnly = true)
    public AnswerSelectionTO findByAnswerSelectionName(final String moduleName, final String userName) {
        final UserEntity userEntity = userService.findByUserName(userName).orElseThrow(
                () -> new UserNotFoundException(userName));
        final List<ClassifierTO> classifiers = classifierService.findAllByUser(userEntity.getUsername());
        return answerSelectionMapper.toTO(answerSelectionRepository.findByAnswerSelectionName(moduleName), classifiers);
    }


    @Transactional
    public AnswerSelectionTO create(final String userName, final String answerSelectionModule) {
        final UserEntity userEntity = userService.findByUserName(userName).orElseThrow(
                () -> new UserNotFoundException(userName));
        final List<ClassifierTO> classifiers = classifierService.findAllByUser(userEntity.getUsername());
        final AnswerSelectionEntity answerSelectionEntity = new AnswerSelectionEntity();
        answerSelectionEntity.setAnswerSelectionName(answerSelectionModule);
        answerSelectionEntity.setUserEntity(userEntity);
        return answerSelectionMapper.toTO(answerSelectionRepository.persist(answerSelectionEntity), classifiers);

    }


    @Transactional(readOnly = true)
    public List<AnswerSelectionTO> findAllForUser(final String userName) {
        final UserEntity userEntity = userService.findByUserName(userName).orElseThrow(
                () -> new UserNotFoundException(userName));
        final List<ClassifierTO> classifiers = classifierService.findAllByUser(userEntity.getUsername());
        return answerSelectionRepository.findAllByUserId(userEntity.getId()).stream().map(answerSelectionEntity -> {
            return answerSelectionMapper.toTO(answerSelectionEntity, classifiers);
        }).collect(Collectors.toList());
    }
}
