package org.alist.infrastructure.persistence.postgresql.config;

import javax.transaction.Transactional;
import java.util.concurrent.Callable;

public interface WithTransaction {
    @Transactional
    default <V> V withTransaction(Callable<V> operation) throws Exception {
        return operation.call();
    }
}
