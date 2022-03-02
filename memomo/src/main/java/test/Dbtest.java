package test;

import java.net.URI;

public class Dbtest {

	public static void main(String[] args) {
		String dbUri = System.getenv("CLEARDB_DATABASE_URL");
		System.out.println(dbUri);

	}

}
