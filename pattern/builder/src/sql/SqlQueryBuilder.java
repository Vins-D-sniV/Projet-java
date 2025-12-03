package sql;

import sql.clause.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlQueryBuilder {
    protected final String table;

    protected List<String> columns = new ArrayList<>();
    protected WhereClause whereClause;
    protected OrderByClause orderByClause;
    protected JoinClause joinClause;
    protected GroupByClause groupByClause;
    protected HavingClause havingClause;

    protected Integer limit;
    protected Integer offset;

    public SqlQueryBuilder(String table){
        if(table==null || table.trim().isEmpty()){
            throw new IllegalArgumentException("Le nom de la table est obligatoire");
        }
        this.table = table;

    }

    public SqlQueryBuilder select(String... columns){
        this.columns.addAll(Arrays.asList(columns));
        return this;
    }
    public SqlQueryBuilder where(String condition){
        this.whereClause=new WhereClause(condition);
        return this;
    }

    public SqlQueryBuilder join(String join){
        this.joinClause = new JoinClause(join);
        return this;
    }

    public  SqlQueryBuilder groupBy (String groupBy){
        this.groupByClause= new GroupByClause(groupBy);
        return this;
    }
    public SqlQueryBuilder having(String having) {
        this.havingClause = new HavingClause(having);
        return this;
    }

    public SqlQueryBuilder orderBy(String orderBy) {
        this.orderByClause = new OrderByClause(orderBy);
        return this;
    }

    public SqlQueryBuilder limit(int limit) {
        if (limit <= 0) throw new IllegalArgumentException("Limit doit être positif");
        this.limit = limit;
        return this;
    }

    public SqlQueryBuilder offset(int offset) {
        if (offset < 0) throw new IllegalArgumentException("Offset ne peut pas être négatif");
        this.offset = offset;
        return this;
    }

    public SqlQuery build(){
        return new SqlQuery(this);
    }
}
