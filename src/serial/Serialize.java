package serial;

import main.ReturnMethod;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialize implements Serializable {


	/**

	public static void main(String[] args) {
		Serialize manage = new Serialize();
		String fullPath = "src/assets/data.manifest";

		ReturnMethod dto = new ReturnMethod("고령화", "1992/12/21", "대한민국",
				new ReturnMethod("1","2","3",null,null), null);
		manage.saveObject(fullPath, dto);
	}

	public void saveObject(String fullPath, ReturnMethod dto) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fullPath);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(dto);
			System.out.println("Write Success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
} */
}
