package org.group2;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {

	public static void main(String[] args) {
		try {
			Quarkus.run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
