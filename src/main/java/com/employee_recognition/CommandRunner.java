package com.employee_recognition;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class CommandRunner implements CommandLineRunner {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	@Override
	public void run(String... args) throws Exception {
		if (!isWindows()) {
			Process pa = Runtime.getRuntime().exec("chmod +x ./src/main/webapp/latex_compiler");
			pa.waitFor();
		}
	}
	
    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }
}
