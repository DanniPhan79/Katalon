package ocb.keywords

public enum DatabaseDriver {
	ORACLE("Oracle","oracle.jdbc.pool.OracleDataSource"),
	MYSQL("MySql","com.mysql.jdbc.Driver"),
	SQLSERVER("Sql Server","com.microsoft.sqlserver.jdbc.SQLServerDriver")

	private String name
	private String driver

	public String getName() {
		return name
	}

	public String getDriver() {
		return driver
	}
}
