package chatty.seq2seq;

import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import chatty.util.AbstractRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty.seq2seq.QSeq2SeqEntity.seq2SeqEntity;

@Singleton
public class Seq2SeqRepository extends AbstractRepository<Seq2SeqEntity> {
    @Override
    protected Class<Seq2SeqEntity> getEntityClass() {
        return Seq2SeqEntity.class;
    }


    @Transactional(readOnly = true)
    public List<Seq2SeqEntity> findAllByUserId(final int userId) {

        return query().from(seq2SeqEntity).where(seq2SeqEntity.userEntity.id.eq(userId)).fetch();

    }


    @Transactional(readOnly = true)
    public Optional<Seq2SeqEntity> findByName(final String seq2SeqName) {
        return Optional.ofNullable(
                query().from(seq2SeqEntity).where(seq2SeqEntity.seq2SeqName.eq(seq2SeqName)).fetchFirst());
    }
}
