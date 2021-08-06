package chatty.dst.control;

import javax.inject.Singleton;
import org.modelmapper.ModelMapper;
import chatty.dst.types.DstEntity;
import chatty.dst.types.DstTO;
import chatty.seq2seq.Seq2SeqTO;
import chatty.util.AbstractMapper;

@Singleton
public class DstMapper extends AbstractMapper<DstEntity, DstTO> {
    @Override
    public Class<DstEntity> getSourceClass() {
        return DstEntity.class;
    }


    @Override
    public Class<DstTO> getTargetClass() {
        return DstTO.class;
    }


    @Override
    public void configureMappings(final ModelMapper mapper) {
        mapper.typeMap(DstEntity.class, DstTO.class)
                .addMapping(src -> src.getUserEntity().getId(), DstTO::setUserId);
    }
}
