package com.khoa.play.repositories;

import com.khoa.play.configs.DatabaseExecutionContext;
import com.khoa.play.configs.JooqContextProvider;
import com.khoa.play.jooq.tables.pojos.Author;
import com.khoa.play.jooq.tables.records.AuthorRecord;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

import static com.khoa.play.jooq.Library.LIBRARY;

public class AsyncAuthorRepository {

    private JooqContextProvider jooq;

    private Executor executor;

    @Inject
    public AsyncAuthorRepository(JooqContextProvider jooq, DatabaseExecutionContext databaseExecutionContext) {
        this.jooq = jooq;
        this.executor = databaseExecutionContext;
    }


    public CompletionStage<List<Author>> getListAuthor() {
        return CompletableFuture.supplyAsync(() ->
                jooq.dsl().select().from(LIBRARY.AUTHOR).fetchInto(Author.class)
        );
    }

    public CompletionStage<List<Author>> getListAuthor2() {
        return CompletableFuture.supplyAsync(
                () -> jooq.dsl().select().from(LIBRARY.AUTHOR).fetchInto(Author.class),
                executor
        );
    }

    public CompletionStage<Integer> updateAuthor(Author author) {
        return jooq.dsl().update(LIBRARY.AUTHOR)
                .set(LIBRARY.AUTHOR.FIRST_NAME, author.getFirstName())
                .set(LIBRARY.AUTHOR.LAST_NAME, author.getLastName())
                .set(LIBRARY.AUTHOR.DATE_OF_BIRTH, author.getDateOfBirth())
                .set(LIBRARY.AUTHOR.YEAR_OF_BIRTH, author.getYearOfBirth())
                .set(LIBRARY.AUTHOR.DISTINGUISHED, author.getDistinguished())
                .where(LIBRARY.AUTHOR.ID.eq(author.getId()))
                .executeAsync();
    }

    public CompletionStage<AuthorRecord> insertAuthor(Author author) {
        return CompletableFuture.supplyAsync(
                () -> jooq.dsl().insertInto(LIBRARY.AUTHOR)
                        .set(LIBRARY.AUTHOR.FIRST_NAME, author.getFirstName())
                        .set(LIBRARY.AUTHOR.LAST_NAME, author.getLastName())
                        .set(LIBRARY.AUTHOR.DATE_OF_BIRTH, author.getDateOfBirth())
                        .set(LIBRARY.AUTHOR.YEAR_OF_BIRTH, author.getYearOfBirth())
                        .set(LIBRARY.AUTHOR.DISTINGUISHED, author.getDistinguished())
                        .returning()
                        .fetchOne()
        );
    }

//    public void insertAuthorTransaction(Author author) {
//        DSL.using(jooq.dsl().parsingConnection()).transaction(
//                configuration -> {
//                    DSL.using(configuration).insertInto(LIBRARY.AUTHOR)
//                            .set(LIBRARY.AUTHOR.FIRST_NAME, author.getFirstName())
//                            .set(LIBRARY.AUTHOR.LAST_NAME, author.getLastName())
//                            .set(LIBRARY.AUTHOR.DATE_OF_BIRTH, author.getDateOfBirth())
//                            .set(LIBRARY.AUTHOR.YEAR_OF_BIRTH, author.getYearOfBirth())
//                            .set(LIBRARY.AUTHOR.DISTINGUISHED, author.getDistinguished())
//                            .execute();
//                    // throw new RuntimeException();
//                }
//        );
//    }

    public void insertAuthorTransaction(Author author) {
        jooq.dsl().transaction(
                configuration -> {
                    DSL.using(configuration).insertInto(LIBRARY.AUTHOR)
                            .set(LIBRARY.AUTHOR.FIRST_NAME, author.getFirstName())
                            .set(LIBRARY.AUTHOR.LAST_NAME, author.getLastName())
                            .set(LIBRARY.AUTHOR.DATE_OF_BIRTH, author.getDateOfBirth())
                            .set(LIBRARY.AUTHOR.YEAR_OF_BIRTH, author.getYearOfBirth())
                            .set(LIBRARY.AUTHOR.DISTINGUISHED, author.getDistinguished())
                            .execute();
                    throw new RuntimeException();
                }
        );
    }
}
