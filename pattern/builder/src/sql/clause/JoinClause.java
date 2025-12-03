package sql.clause;

public class JoinClause {
    private  final String join;

    public JoinClause(String join) {
        this.join = join;
    }
    public String toSql(){
        return  join;
    }
}
