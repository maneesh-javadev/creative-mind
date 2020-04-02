package in.nic.pes.lgd.types.hibernate;

import org.hibernate.dialect.PostgreSQLDialect;
import java.sql.Types;

public class ExtendedPS90Dialect extends PostgreSQLDialect {
	public ExtendedPS90Dialect() {
        super();
        this.registerColumnType( Types.JAVA_OBJECT, "jsonb" );
        this.registerColumnType( Types.OTHER, "json" );
        this.registerHibernateType(Types.OTHER, "in.nic.pes.lgd.types.hibernate.JsonType");
    }
}
