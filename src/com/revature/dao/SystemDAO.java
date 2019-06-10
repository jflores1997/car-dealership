package com.revature.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.revature.pojo.DealershipSys;

public class SystemDAO {

	public static void saveSystem(DealershipSys system, String fileName) {
		ObjectOutputStream oos = null;
		FileOutputStream foo = null;
		
		try {
			foo = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(foo);
			oos.writeObject(system);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (foo != null) foo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static DealershipSys loadSystem(String fileName) {
		DealershipSys ret = null;
		
		//try with resources
		try (FileInputStream fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			
			ret = (DealershipSys) ois.readObject();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return ret;

	}
	
}
