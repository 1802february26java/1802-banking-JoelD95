package com.revature.repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InFile {
	public InFile(){		
	}
	public String getFile() {
		String input="";
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		try {
			input = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;

	}

}
