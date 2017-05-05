package com.timxyz;

import com.timxyz.models.AccessLog;
import com.timxyz.repositories.AccessLogRepository;
import com.timxyz.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by muhamed on 5/4/17.
 */
public class LogHelper {
    @Autowired
    private static AccessLogRepository accessLogRepository;

    public static void log(Long userId, String type, String description, String tableName) {
        AccessLog accessLog = new AccessLog();

        accessLog.setDate(Timestamp.valueOf(LocalDateTime.now()));
        accessLog.setType(type);
        accessLog.setDescription(description);
        accessLog.setTableName(tableName);
        accessLog.setAccount(new AccountService().get(userId));

        accessLogRepository.save(accessLog);
    }
}
