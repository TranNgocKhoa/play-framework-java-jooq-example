package com.khoa.play.configs;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderKeywordStyle;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import play.db.*;

import javax.inject.Inject;

public class JooqContextProvider {

    @Inject
    private Database db;

    public DSLContext dsl() {
        return DSL.using(
                new JooqConnectionProvider(db),
                SQLDialect.MYSQL,
                new Settings()
                        .withExecuteLogging(false)
                        .withRenderSchema(false)
                        .withRenderFormatted(false)
                        .withRenderNameStyle(RenderNameStyle.QUOTED)
                        .withRenderKeywordStyle(RenderKeywordStyle.UPPER)
        );
    }
}