package chatty.seq2seq;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.user.UserEntity;
import chatty.user.UserService;
import chatty.util.exceptions.ChattyException;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class Seq2SeqService {


    @Inject
    private UserService userService;

    @Inject
    private Seq2SeqRepository seq2SeqRepository;


    @Inject
    private Seq2SeqMapper seq2SeqMapper;


    @Transactional
    public List<Seq2SeqTO> getAllSeq2SeqForUser(final String userName) {

        final UserEntity userEntity = userService.findByUserName(userName).orElseThrow(
                () -> new ChattyException("Could not find user"));
        return seq2SeqRepository.findAllByUserId(userEntity.getId()).stream().map(seq2SeqMapper::toTO).collect(
                Collectors.toList());

    }


    @Transactional
    public Seq2SeqTO createSeq2SeqForUser(final String seq2SeqName, final String fileName,
                                          final UserEntity userEntity) {
        Seq2SeqEntity seq2SeqEntity = new Seq2SeqEntity();
        seq2SeqEntity.setSeq2SeqName(seq2SeqName);
        seq2SeqEntity.setFileName(fileName);
        seq2SeqEntity.setUserEntity(userEntity);
        return seq2SeqMapper.toTO(seq2SeqRepository.persist(seq2SeqEntity));
    }


    @Transactional(readOnly = true)
    public Seq2SeqTO getSeq2SeqById(final String seq2SeqId) {
        final Optional<Seq2SeqEntity> seq2SeqModuleById = seq2SeqRepository.findById(Integer.valueOf((seq2SeqId)));
        if (seq2SeqModuleById.isPresent()) {
            return seq2SeqMapper.toTO(seq2SeqModuleById.get());
        } else {
            throw new ChattyException("Could not find seq2seq Module");
        }


    }


    @Transactional
    public Seq2SeqTO updateSeq2Seq(final String seq2SeqName, final boolean isTrained) {
        final Optional<Seq2SeqEntity> seq2SeqModule = seq2SeqRepository.findByName(seq2SeqName);
        if (seq2SeqModule.isPresent()) {
            final Seq2SeqEntity seq2SeqEntity = seq2SeqModule.get();
            seq2SeqEntity.setTrain(isTrained);
            return seq2SeqMapper.toTO(seq2SeqEntity);
        } else {
            throw new ChattyException("Could not find seq2seq Module");
        }


    }

}
