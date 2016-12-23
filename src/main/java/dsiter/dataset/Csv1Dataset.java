package dsiter.dataset;

import dsiter.dataset.IDataset;
import dsiter.dataset.csv.CsvDataset;
import dsiter.iterator.IDatasetIterator;
import dsiter.reader.FileReaderFactory;
import dsiter.reader.IReaderFactory;
import dsiter.row.ColumnDescriptor;
import dsiter.row.ColumnType;
import org.apache.commons.csv.CSVFormat;

public class Csv1Dataset implements IDataset {

	private CsvDataset innerDataset;

	public Csv1Dataset() {
		String filename = getClass().getResource("csv1.csv").getPath();

		// Transaction_date,Product,Price,Payment_Type,Name,City,State,Country,Account_Created,Last_Login,Latitude,Longitude
		ColumnDescriptor[] columns = new ColumnDescriptor[] {
			new ColumnDescriptor("Transaction Date", ColumnType.STRING, -1),
			new ColumnDescriptor("Product", ColumnType.STRING, -1),
			new ColumnDescriptor("Price", ColumnType.INT, -1),
			new ColumnDescriptor("Payment Type", ColumnType.STRING, -1),
			new ColumnDescriptor("Name", ColumnType.STRING, -1),
			new ColumnDescriptor("City", ColumnType.STRING, -1),
			new ColumnDescriptor("State", ColumnType.STRING, -1),
			new ColumnDescriptor("Country", ColumnType.STRING, -1),
			new ColumnDescriptor("Account Created", ColumnType.STRING, -1),
			new ColumnDescriptor("Last Login", ColumnType.STRING, -1),
			new ColumnDescriptor("Latitude", ColumnType.DOUBLE, -1),
			new ColumnDescriptor("Longitude", ColumnType.DOUBLE, -1)
		};

		innerDataset = new CsvDataset(
			new FileReaderFactory(filename),
			columns,
			CSVFormat.RFC4180.withSkipHeaderRecord()
		);
	}

	@Override
	public IDatasetIterator getIterator() throws Exception {
		return innerDataset.getIterator();
	}
}
