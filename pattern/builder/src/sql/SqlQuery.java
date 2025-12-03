package sql;

import sql.clause.*;

import java.util.List;

public class SqlQuery {
    private final String table;
    private final List<String> columns;
    private final WhereClause whereClause;
    private  final JoinClause joinClause;
    private final GroupByClause groupByClause;
    private final HavingClause havingClause;

    private final OrderByClause orderByClause;
    private final Integer limit;
    private final Integer offset;

    protected SqlQuery(SqlQueryBuilder builder){
        this.table = builder.table;
        this.columns = builder.columns;
        this.whereClause = builder.whereClause;
        this.joinClause = builder.joinClause;
        this.groupByClause = builder.groupByClause;
        this.havingClause = builder.havingClause;
        this.orderByClause = builder.orderByClause;
        this.limit = builder.limit;
        this.offset = builder.offset;

    }

    public String toSql() {
        StringBuilder sql = new StringBuilder("SELECT ");

        sql.append(columns == null || columns.isEmpty()
                ? "*"
                : String.join(", ", columns));

        sql.append(" FROM ").append(table);

        if (joinClause != null) sql.append(" ").append(joinClause.toSql());
        if (whereClause != null) sql.append(" ").append(whereClause.toSql());
        if (groupByClause != null) sql.append(" ").append(groupByClause.toSql());
        if (havingClause != null) sql.append(" ").append(havingClause.toSql());
        if (orderByClause != null) sql.append(" ").append(orderByClause.toSql());
        if (limit != null) sql.append(" LIMIT ").append(limit);
        if (offset != null) sql.append(" OFFSET ").append(offset);

        return sql.toString();
    }
}
