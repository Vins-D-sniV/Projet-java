import sql.SqlQuery;
import sql.SqlQueryBuilder;

public class Main {
    public static void main(String[] args) {
        SqlQuery query = new SqlQueryBuilder("users")
                .select("id", "name", "email")
                .join("LEFT JOIN orders ON orders.user_id = users.id")
                .where("age > 18")
                .groupBy("country")
                .having("COUNT(*) > 5")
                .orderBy("name ASC")
                .limit(20)
                .offset(10)
                .build();

        System.out.println(query.toSql());

    }
}