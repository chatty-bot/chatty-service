package chatty.dst.boundary;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.dst.control.DstService;
import chatty.dst.types.DstTO;
import chatty.user.UserEntity;
import chatty.user.UserService;
import chatty.util.exceptions.ChattyException;

@Singleton
public class DstFacade {

    @Inject
    private DstService dstService;
    @Inject
    private UserService userService;


    private UserEntity getUser(final String userName) {
        return userService.findByUserName(userName).orElseThrow(
                () -> new ChattyException("Could not find user with name " + userName));
    }


    public DstTO createNewDst(final String userName, final DstTO newDst) {
        final UserEntity user = getUser(userName);
        return dstService.createNewDst(user, newDst);
    }


    public DstTO findDstByName(final String userName, final String dstName) {
        final UserEntity user = getUser(userName);
        return dstService.findDstByName(user, dstName);
    }


    public List<DstTO> findAllForUser(final String userName) {
        final UserEntity user = getUser(userName);
        return dstService.findAllForUser(user);
    }


    public DstTO updateDst(final String userName, final DstTO updatedDst) {
        final UserEntity user = getUser(userName);
        return dstService.updateDst(user, updatedDst);
    }
}
