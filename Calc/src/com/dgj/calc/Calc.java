package com.dgj.calc;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Calc {
	public static void main(final String[] args) {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File("data.txt")));

			SimpleRegression sr = new SimpleRegression(true);
			for (Object key : properties.keySet()) {
				int index = Integer.parseInt(key.toString());
				String value = properties.getProperty(key.toString());
				if (!value.isEmpty()) {
					sr.addData(index, Double.parseDouble(value));
				}
			}

			System.out.println("y = " + sr.getSlope() + " * x + " + sr.getIntercept());

			System.out.println("请输入数字");

			Scanner scanner = new Scanner(System.in);
			while (scanner.hasNextLine()) {
				String in = scanner.nextLine();

				try {
					int count = Integer.parseInt(in);
					System.out.println(sr.predict(count));
				} catch (Exception e) {
					System.out.println("请输入数字");
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
