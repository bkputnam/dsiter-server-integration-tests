package dsiter.servlet;

import dsiter.dataset.IDataset;
import dsiter.dataset.csv.CsvDataset;
import dsiter.reader.FileReaderFactory;
import dsiter.reader.IReaderFactory;
import dsiter.row.ColumnDescriptor;
import dsiter.row.ColumnType;
import dsiter.row.RowCopier;
import dsiter.row.RowShape;
import dsiter.server.DatasetServlet;
import org.apache.commons.csv.CSVFormat;

import java.io.*;

public class Csv1Servlet extends DatasetServlet {

	public Csv1Servlet() {
		super(getDataset());
	}

	private static IDataset getDataset() {

		// Transaction_date,Product,Price,Payment_Type,Name,City,State,Country,Account_Created,Last_Login,Latitude,Longitude
		ColumnDescriptor[] columns = new ColumnDescriptor[] {
			new ColumnDescriptor("Transaction_date", ColumnType.STRING, -1),
			new ColumnDescriptor("Product", ColumnType.STRING, -1),
			new ColumnDescriptor("Price", ColumnType.INT, -1),
			new ColumnDescriptor("Payment_Type", ColumnType.STRING, -1),
			new ColumnDescriptor("Name", ColumnType.STRING, -1),
			new ColumnDescriptor("City", ColumnType.STRING, -1),
			new ColumnDescriptor("State", ColumnType.STRING, -1),
			new ColumnDescriptor("Country", ColumnType.STRING, -1),
			new ColumnDescriptor("Account_Created", ColumnType.STRING, -1),
			new ColumnDescriptor("Last_Login", ColumnType.STRING, -1),
			new ColumnDescriptor("Latitude", ColumnType.DOUBLE, -1),
			new ColumnDescriptor("Longitude", ColumnType.DOUBLE, -1)
		};

		// Quick hack to use RowCopier to re-number the columns for us.
		RowCopier rc = new RowCopier(columns);
		columns = rc.getDestColumnDescriptors();

		return new CsvDataset(
			new CustomFileReaderFactory(),
			columns,
			CSVFormat.RFC4180.withSkipHeaderRecord()
		);
	}

	private static class CustomFileReaderFactory implements IReaderFactory {
		@Override
		public Reader getReader() throws Exception {
			String resource = "csv1.csv";
			InputStream is = Csv1Servlet.class.getClassLoader().getResourceAsStream(resource);
			if (is == null) {
				throw new FileNotFoundException("Failed to find resource '" + resource + "'");
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			// TODO - figure out a better way to skip the first (header) line
			br.readLine();

			return br;
		}
	}
}
