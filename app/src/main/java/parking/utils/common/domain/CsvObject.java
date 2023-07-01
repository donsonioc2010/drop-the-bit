package parking.utils.common.domain;

public interface CsvObject {
	String toCsvString();

	String fromCsvString(String csv);
}
