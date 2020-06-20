package com.bootstudy.study;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final TransactionTemplate transactionTemplate;
    private final JdbcRoomDao jdbcRoomDao;

    @Override
    public Room getRoom(String roomId) {
        return jdbcRoomDao.getRoomById(roomId);
    }

    @Override
    public void insertRoom(Room room) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            // 이 메서드 안쪽이 트랜젹션 범위가 된다.
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbcRoomDao.insertRoom(room);
            }
            // 위 메서드가 정상종료되면 자동으로 트랜잭션을 커밋한다. 예외가 있으면 롤백한다.
        });
    }


}
