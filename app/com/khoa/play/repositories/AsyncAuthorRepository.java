package com.khoa.play.repositories;

import com.khoa.play.configs.JooqContextProvider;
import com.khoa.play.jooq.tables.pojos.Author;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static com.khoa.play.jooq.Library.LIBRARY;

public class AsyncAuthorRepository {

    private JooqContextProvider jooq;

    @Inject
    public AsyncAuthorRepository(JooqContextProvider jooq) {
        this.jooq = jooq;
    }


    public CompletionStage<List<Author>> getListAuthor() {
        return CompletableFuture.supplyAsync(() ->
                jooq.dsl().select().from(LIBRARY.AUTHOR).fetchInto(Author.class)
        );
    }
}
