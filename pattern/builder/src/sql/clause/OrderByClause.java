package sql.clause;

public class OrderByClause {
    private final String orderBy;

    public OrderByClause(String orderBy) {
        this.orderBy = orderBy;
    }
    public String toSql(){
        return "ORDER BY " + orderBy;
    }
}
