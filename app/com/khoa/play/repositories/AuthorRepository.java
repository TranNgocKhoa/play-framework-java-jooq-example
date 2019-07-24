package com.khoa.play.repositories;

import com.khoa.play.configs.JooqContextProvider;
import com.khoa.play.jooq.tables.pojos.Author;
import com.khoa.play.jooq.tables.records.AuthorRecord;

import javax.inject.Inject;
import java.util.List;

import static com.khoa.play.jooq.Library.LIBRARY;

public class AuthorRepository {

    private JooqContextProvider jooq;

    @Inject
    public AuthorRepository(JooqContextProvider jooq) {
        this.jooq = jooq;
    }

    public List<Author> getListAuthor() {
        return jooq.dsl()
                .select()
                .from(LIBRARY.AUTHOR)
                .fetchInto(Author.class);
    }

    public Author createAuthor(Author author) {
        AuthorRecord record = jooq.dsl()
                .insertInto(LIBRARY.AUTHOR, LIBRARY.AUTHOR.FIRST_NAME, LIBRARY.AUTHOR.LAST_NAME)
                .values(author.getFirstName(), author.getLastName())
                .returning()
                .fetch().get(0);
        return new Author(record.getId(), record.getFirstName(), record.getLastName());
    }

    public Author updateAuthor(Author author) {
        AuthorRecord record = jooq.dsl().update(LIBRARY.AUTHOR)
                .set(LIBRARY.AUTHOR.FIRST_NAME, author.getFirstName())
                .set(LIBRARY.AUTHOR.LAST_NAME, author.getLastName())
                .where(LIBRARY.AUTHOR.ID.eq(author.getId()))
                .returning().fetchOne();
        return new Author(record.getId(), record.getFirstName(), record.getLastName());
    }

    public boolean deleteAuthor(Integer id) {
        try {
            Author author = jooq.dsl()
                    .select()
                    .from(LIBRARY.AUTHOR)
                    .fetchInto(Author.class).get(0);
            if (author != null) {
                jooq.dsl().delete(LIBRARY.AUTHOR)
                        .where(LIBRARY.AUTHOR.ID.eq(author.getId()))
                        .execute();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
