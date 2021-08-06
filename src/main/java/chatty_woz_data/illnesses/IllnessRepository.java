package chatty_woz_data.illnesses;

import javax.inject.Singleton;
import chatty.util.AbstractRepository;

@Singleton
public class IllnessRepository extends AbstractRepository<IllnessEntity> {
    @Override
    protected Class<IllnessEntity> getEntityClass() {
        return IllnessEntity.class;
    }
}
