package chatty.dst.entity;

import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import chatty.dst.types.DstEntity;
import chatty.util.AbstractRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty.dst.types.QDstEntity.dstEntity;

@Singleton
public class DstRepository extends AbstractRepository<DstEntity> {
    @Override
    protected Class<DstEntity> getEntityClass() {
        return DstEntity.class;
    }


    @Transactional
    public Optional<DstEntity> findByName(final int userId, final String dstName) {
        return Optional.ofNullable(query().select(dstEntity).from(dstEntity).where(
                dstEntity.userEntity.id.eq(userId).and(dstEntity.dstName.eq(dstName))).fetchFirst());
    }


    @Transactional
    public List<DstEntity> findAllByUserId(final int userId) {
        return query().select(dstEntity).from(dstEntity).where(
                dstEntity.userEntity.id.eq(userId)).fetch();
    }
}
