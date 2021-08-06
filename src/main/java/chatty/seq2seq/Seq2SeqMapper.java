package chatty.seq2seq;

import javax.inject.Singleton;
import org.modelmapper.ModelMapper;
import chatty.util.AbstractMapper;

@Singleton
public class Seq2SeqMapper extends AbstractMapper<Seq2SeqEntity, Seq2SeqTO> {
    @Override
    public Class<Seq2SeqEntity> getSourceClass() {
        return Seq2SeqEntity.class;
    }


    @Override
    public Class<Seq2SeqTO> getTargetClass() {
        return Seq2SeqTO.class;
    }


    @Override
    public void configureMappings(final ModelMapper mapper) {

        mapper.typeMap(Seq2SeqEntity.class, Seq2SeqTO.class)
                .addMapping(src -> src.getUserEntity().getId(), Seq2SeqTO::setUserId);

    }
}
