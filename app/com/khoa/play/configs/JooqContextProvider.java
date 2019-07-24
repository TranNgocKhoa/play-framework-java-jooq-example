package com.khoa.play.configs;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import play.db.*;

import javax.inject.Inject;

public class JooqContextProvider {

    @Inject
    private Database db;

    public DSLContext dsl() {
        return DSL.using(
            new JooqConnectionProvider(db),
            SQLDialect.MYSQL);
    }
}