package sql.clause;

public class HavingClause {
    private final String having;

    public HavingClause(String having) {
        this.having = having;
    }

    public String toSql(){
        return "HAVING " + having;
    }
}
