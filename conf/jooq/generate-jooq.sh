#!/usr/bin/env bash
java -classpath jooq-3.11.11.jar:jooq-meta-3.11.11.jar:jooq-codegen-3.11.11.jar:mysql-connector-java-5.1.41.jar:. org.jooq.codegen.GenerationTool library.xml