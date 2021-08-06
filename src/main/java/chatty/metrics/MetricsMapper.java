package chatty.metrics;

import javax.inject.Singleton;
import org.modelmapper.ModelMapper;
import chatty.util.AbstractMapper;

@Singleton
public class MetricsMapper extends AbstractMapper<MetricsEntity, MetricsTO> {


    public MetricsEntity toEntity(final MetricsMessage metricsMessage) {
        final MetricsEntity metricsEntity = new MetricsEntity();
        metricsEntity.setPrecision(metricsMessage.getPrecision());
        metricsEntity.setIntentName(metricsMessage.getIntentName());

        return metricsEntity;
    }


    @Override
    public Class<MetricsEntity> getSourceClass() {
        return MetricsEntity.class;
    }


    @Override
    public Class<MetricsTO> getTargetClass() {
        return MetricsTO.class;
    }


    @Override
    public void configureMappings(final ModelMapper mapper) {

    }
}
