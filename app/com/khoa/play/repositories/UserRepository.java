package com.khoa.play.repositories;

import com.khoa.play.configs.DatabaseExecutionContext;
import com.khoa.play.configs.JooqContextProvider;
import com.khoa.play.jooq.tables.pojos.User;
import com.khoa.play.jooq.tables.records.AuthorRecord;
import com.khoa.play.jooq.tables.records.UserRecord;

import javax.inject.Inject;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static com.khoa.play.jooq.Library.LIBRARY;

/**
 * @author Khoa
 * @created 7/28/2019
 */
public class UserRepository {

    private DatabaseExecutionContext executor;
    private JooqContextProvider jooq;

    @Inject
    public UserRepository(DatabaseExecutionContext databaseExecutionContext, JooqContextProvider jooq) {
        this.executor = databaseExecutionContext;
        this.jooq = jooq;
    }

    public CompletionStage<UserRecord> getUserByEmail(String email) {
        return CompletableFuture.supplyAsync(
                () -> jooq.dsl().selectFrom(LIBRARY.USER)
                        .where(LIBRARY.USER.EMAIL.eq(email)).fetchAny(), executor);
    }

    public CompletionStage<UserRecord> createUser(User user) {
        return CompletableFuture.supplyAsync(() -> jooq.dsl().insertInto(LIBRARY.USER)
                .set(LIBRARY.USER.EMAIL, user.getEmail())
                .set(LIBRARY.USER.PASSWORD, user.getPassword())
                .set(LIBRARY.USER.STATUS, user.getStatus())
                .returning()
                .fetchOne());
    }
}
