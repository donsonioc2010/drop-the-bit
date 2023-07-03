package parking.utils.common.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import parking.utils.common.domain.CsvObject;
import parking.utils.log.Log;

public class MakeCSV {
	public static <T extends CsvObject> void saveCsv(String fileName, List<T> list) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

			for (CsvObject obj : list) {
				pw.println(obj.toCsvString());
			}
			pw.close();
		} catch (IOException e) {
			System.out.println(Log.error(String.format("IOException : %s 파일 IO 에러", fileName)));
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends CsvObject> void loadCsv(String fileName, List<T> list, Class<T> clazz) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				System.out.println(Log.warn(fileName + "파일 생성"));
				file.createNewFile();
			}

			Method method = clazz.getDeclaredMethod("fromCsvString", String.class);

			BufferedReader br = new BufferedReader(new FileReader(fileName));

			String line = null;
			while ((line = br.readLine()) != null) {
				list.add((T)method.invoke(null, line));
			}
			br.close();
		} catch (NoSuchMethodException e) {
			System.out.println(Log.error(String.format("NoSuchMethodException : %s 읽는 중 오류 발생", fileName)));
		} catch (FileNotFoundException e) {
			System.out.println(Log.error(String.format("FileNotFoundException : %s 파일 존재 유무 확인해주세요", fileName)));
		} catch (IOException e) {
			System.out.println(Log.error(String.format("IOException : %s 파일 IO 에러", fileName)));
		} catch (InvocationTargetException | IllegalAccessException e) {
			System.out.println(Log.error(String.format("Invocation, Illegal Exception : fileName : %s\n", fileName)));
		}
	}
}
