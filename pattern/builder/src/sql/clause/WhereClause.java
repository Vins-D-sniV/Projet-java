package sql.clause;

public class WhereClause {
    private final String condition;


    public WhereClause(String condition) {
        this.condition = condition;
    }
    public String toSql(){
        return "WHERE " + condition;
    }
}
