package sql.clause;

public class GroupByClause {
    private final String groupBy;

    public GroupByClause(String groupBy) {
        this.groupBy = groupBy;
    }

    public String toSql(){
        return "GROUP BY " + groupBy;
    }

}
