package chatty.user;


import java.util.Optional;
import javax.inject.Singleton;
import chatty.util.AbstractRepository;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class UserRepository extends AbstractRepository<UserEntity> {


    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }


    @Transactional
    public Optional<UserEntity> findByUserName(final String userName) {
        return Optional.ofNullable(
                query().select(QUserEntity.userEntity).from(QUserEntity.userEntity).where(
                        QUserEntity.userEntity.username.eq(userName)).fetchOne());
    }
}
