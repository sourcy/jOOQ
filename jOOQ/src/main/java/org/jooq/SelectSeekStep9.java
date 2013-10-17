/**
 * Copyright (c) 2009-2013, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 *
 * This work is dual-licensed
 * - under the Apache Software License 2.0 (the "ASL")
 * - under the jOOQ License and Maintenance Agreement (the "jOOQ License")
 * =============================================================================
 * You may choose which license applies to you:
 *
 * - If you're using this work with Open Source databases, you may choose
 *   either ASL or jOOQ License.
 * - If you're using this work with at least one commercial database, you must
 *   choose jOOQ License
 *
 * For more information, please visit http://www.jooq.org/licenses
 *
 * Apache Software License 2.0:
 * -----------------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * jOOQ License and Maintenance Agreement:
 * -----------------------------------------------------------------------------
 * Data Geekery grants the Customer the non-exclusive, timely limited and
 * non-transferable license to install and use the Software under the terms of
 * the jOOQ License and Maintenance Agreement.
 *
 * This library is distributed with a LIMITED WARRANTY. See the jOOQ License
 * and Maintenance Agreement for more details: http://www.jooq.org/licensing
 */
package org.jooq;

import javax.annotation.Generated;

import org.jooq.Record;

/**
 * This type is used for the {@link Select}'s DSL API when selecting generic
 * {@link Record} types.
 * <p>
 * Example: <code><pre>
 * -- get all authors' first and last names, and the number
 * -- of books they've written in German, if they have written
 * -- more than five books in German in the last three years
 * -- (from 2011), and sort those authors by last names
 * -- limiting results to the second and third row
 *
 *   SELECT T_AUTHOR.FIRST_NAME, T_AUTHOR.LAST_NAME, COUNT(*)
 *     FROM T_AUTHOR
 *     JOIN T_BOOK ON T_AUTHOR.ID = T_BOOK.AUTHOR_ID
 *    WHERE T_BOOK.LANGUAGE = 'DE'
 *      AND T_BOOK.PUBLISHED > '2008-01-01'
 * GROUP BY T_AUTHOR.FIRST_NAME, T_AUTHOR.LAST_NAME
 *   HAVING COUNT(*) > 5
 * ORDER BY T_AUTHOR.LAST_NAME ASC NULLS FIRST
 *    LIMIT 2
 *   OFFSET 1
 *      FOR UPDATE
 *       OF FIRST_NAME, LAST_NAME
 *       NO WAIT
 * </pre></code> Its equivalent in jOOQ <code><pre>
 * create.select(TAuthor.FIRST_NAME, TAuthor.LAST_NAME, create.count())
 *       .from(T_AUTHOR)
 *       .join(T_BOOK).on(TBook.AUTHOR_ID.equal(TAuthor.ID))
 *       .where(TBook.LANGUAGE.equal("DE"))
 *       .and(TBook.PUBLISHED.greaterThan(parseDate('2008-01-01')))
 *       .groupBy(TAuthor.FIRST_NAME, TAuthor.LAST_NAME)
 *       .having(create.count().greaterThan(5))
 *       .orderBy(TAuthor.LAST_NAME.asc().nullsFirst())
 *       .limit(2)
 *       .offset(1)
 *       .forUpdate()
 *       .of(TAuthor.FIRST_NAME, TAuthor.LAST_NAME)
 *       .noWait();
 * </pre></code> Refer to the manual for more details
 *
 * @author Lukas Eder
 */
@Generated("This class was generated using jOOQ-tools")
public interface SelectSeekStep9<R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9> extends SelectLimitStep<R> {

    /**
     * Add a synthetic <code>SEEK</code> clause to the query.
     * <p>
     * The synthetic <code>SEEK</code> clause is an alternative way to specify
     * an <code>OFFSET</code>, and thus to perform paging in a SQL query. This
     * can be advantageous for two reasons:
     * <p>
     * <ol>
     * <li>The SQL generated from the <code>SEEK</code> clause is a regular
     * predicate, which can be used by query plan optimisers to choose an
     * appropriate index. The SQL standard <code>OFFSET</code> clause will need
     * to skip <code>N</code> rows in memory.</li>
     * <li>The <code>SEEK</code> clause is stable with respect to new data being
     * inserted or data being deleted while paging through pages.</li>
     * </ol>
     * <p>
     * Example: <code><pre>
     * DSL.using(configuration)
     *    .selectFrom(TABLE)
     *    .orderBy(ID, CODE)
     *    .seek(3, 'abc')
     *    .fetch();
     * </pre></code>
     * <p>
     * The above query will render the following SQL statement:
     * <p>
     * <code><pre>
     * SELECT * FROM table
     * WHERE (id, code) > (3, 'abc')
     * ORDER BY id ASC, code ASC
     * </pre></code>
     * <p>
     * The actual row value expression predicate may be expanded into this
     * equivalent predicate:
     * <p>
     * <code><pre>
     * WHERE (id > 3) OR (id = 3 AND code > 'abc')
     * </pre></code>
     * <p>
     * The <code>SEEK</code> method currently does not support seeking
     * <code>NULL</code> values, or operating with <code>NULLS FIRST</code>,
     * <code>NULLS LAST</code> clauses in the <code>ORDER BY</code> clause.
     *
     * @see <a
     *      href="http://use-the-index-luke.com/sql/partial-results/fetch-next-page">http://use-the-index-luke.com/sql/partial-results/fetch-next-page</a>
     */
    SelectSeekLimitStep<R> seek(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9);

    /**
     * Add a synthetic <code>SEEK</code> clause to the query.
     * <p>
     * The synthetic <code>SEEK</code> clause is an alternative way to specify
     * an <code>OFFSET</code>, and thus to perform paging in a SQL query. This
     * can be advantageous for two reasons:
     * <p>
     * <ol>
     * <li>The SQL generated from the <code>SEEK</code> clause is a regular
     * predicate, which can be used by query plan optimisers to choose an
     * appropriate index. The SQL standard <code>OFFSET</code> clause will need
     * to skip <code>N</code> rows in memory.</li>
     * <li>The <code>SEEK</code> clause is stable with respect to new data being
     * inserted or data being deleted while paging through pages.</li>
     * </ol>
     * <p>
     * Example: <code><pre>
     * DSL.using(configuration)
     *    .selectFrom(TABLE)
     *    .orderBy(ID, CODE)
     *    .seek(3, 'abc')
     *    .fetch();
     * </pre></code>
     * <p>
     * The above query will render the following SQL statement:
     * <p>
     * <code><pre>
     * SELECT * FROM table
     * WHERE (id, code) > (3, 'abc')
     * ORDER BY id ASC, code ASC
     * </pre></code>
     * <p>
     * The actual row value expression predicate may be expanded into this
     * equivalent predicate:
     * <p>
     * <code><pre>
     * WHERE (id > 3) OR (id = 3 AND code > 'abc')
     * </pre></code>
     * <p>
     * The <code>SEEK</code> method currently does not support seeking
     * <code>NULL</code> values, or operating with <code>NULLS FIRST</code>,
     * <code>NULLS LAST</code> clauses in the <code>ORDER BY</code> clause.
     *
     * @see <a
     *      href="http://use-the-index-luke.com/sql/partial-results/fetch-next-page">http://use-the-index-luke.com/sql/partial-results/fetch-next-page</a>
     */
    SelectSeekLimitStep<R> seek(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9);
}
