package chatty.dst.control;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.dst.entity.DstRepository;
import chatty.dst.types.DstEntity;
import chatty.dst.types.DstTO;
import chatty.user.UserEntity;
import chatty.util.exceptions.ChattyException;
import io.micronaut.spring.tx.annotation.Transactional;


@Singleton
public class DstService {

    @Inject
    private DstRepository dstRepository;

    @Inject
    private DstMapper dstMapper;


    @Transactional
    public DstTO createNewDst(final UserEntity userEntity, final DstTO newDst) {
        final DstEntity newEntity = dstMapper.toBE(newDst);
        newEntity.setUserEntity(userEntity);

        final DstEntity persist = dstRepository.persist(newEntity);
        return dstMapper.toTO(persist);
    }


    @Transactional(readOnly = true)
    public DstTO findDstByName(final UserEntity userEntity, final String dstName) {
        final DstEntity byName = dstRepository.findByName(userEntity.getId(), dstName).orElseThrow(
                () -> new ChattyException("Could not find dst with that name"));
        return dstMapper.toTO(byName);
    }


    @Transactional(readOnly = true)
    public List<DstTO> findAllForUser(final UserEntity userEntity) {
        final List<DstEntity> dstEntities = dstRepository.findAllByUserId(userEntity.getId());
        return dstEntities.stream().map(dstMapper::toTO).collect(Collectors.toList());
    }


    @Transactional
    public DstTO updateDst(final UserEntity userEntity, final DstTO updatedDst) {
        final DstEntity dstEntity = dstRepository.findById(updatedDst.getId()).orElseThrow(
                () -> new ChattyException("Could not find dst with that id"));
        dstEntity.setDstName(updatedDst.getDstName());
        dstEntity.setFileName(updatedDst.getFileName());
        dstEntity.setTrain(updatedDst.isTrain());
        final DstEntity mergedEntity = dstRepository.merge(dstEntity);
        return dstMapper.toTO(mergedEntity);
    }


    public DstEntity findById(final String dstId) {
        return dstRepository.findById(Integer.valueOf(dstId)).orElseThrow(
                () -> new ChattyException("could not find dst module with the given id"));
    }
}
