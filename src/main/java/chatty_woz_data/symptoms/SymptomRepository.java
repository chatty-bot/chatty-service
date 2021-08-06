package chatty_woz_data.symptoms;

import javax.inject.Singleton;
import chatty.util.AbstractRepository;

@Singleton
public class SymptomRepository extends AbstractRepository<SymptomEntity> {
    @Override
    protected Class<SymptomEntity> getEntityClass() {
        return SymptomEntity.class;
    }
}
