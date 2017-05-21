package com.timxyz.services;

import com.timxyz.models.AccessLog;
import com.timxyz.models.Account;
import com.timxyz.models.BaseModel;
import com.timxyz.repositories.AccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class LogHelperService {
    @Autowired
    private static AccessLogRepository accessLogRepository;
    private static final String descriptionTemplate = "User %s %s object with id %d of table %s on %s";
    // Username, action, object id, table name, date and time

    private static String getTableName(BaseModel model) {
        // This is a terrible solution but it should work
        String className = model.getClass().toString();

        if (className.equals("Role")) {
            return "roles";
        }
        else {
            return className.substring(0, 1).toLowerCase() + className.substring(1);
        }
    }

    private static void log(Account account, BaseModel model, String action) {
        String username = account.getUsername();
        Long objectId = model.getId();
        String tableName = getTableName(model);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        String description =
                String.format(descriptionTemplate, username, action, objectId, tableName, timestamp.toString());

        AccessLog accessLog = new AccessLog();
        accessLog.setAccount(account);
        accessLog.setDate(timestamp);
        accessLog.setDescription(description);
        accessLog.setObjectId(objectId);
        accessLog.setType(action.toUpperCase()); // For now, can be improved (e.g. should be SELECT instead of SELECTED)
        accessLog.setTableName(tableName);

        accessLogRepository.save(accessLog);
    }

    public static void logCreate(Account account, BaseModel model) {
        log(account, model, "created");
    }

    // suvisno - ubrzo ce biti obrisano
    public static void logSelect(Account account, BaseModel model) {
        log(account, model, "selected");
    }

    public static void logUpdate(Account account, BaseModel model) {
        log(account, model, "updated");
    }

    public static void logDelete(Account account, BaseModel model) {
        log(account, model, "deleted");
    }
}
