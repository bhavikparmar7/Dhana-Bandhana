package in.dhanab.avgraha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.aspose.pdf.AbsorbedCell;
import com.aspose.pdf.AbsorbedRow;
import com.aspose.pdf.AbsorbedTable;
import com.aspose.pdf.Document;
import com.aspose.pdf.TableAbsorber;
import com.aspose.pdf.TextFragmentCollection;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.PageIterator;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

@SpringBootApplication
@EnableMongoRepositories
public class AvgrahaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvgrahaApplication.class, args);
        //backup();
        //tabulaa();
        //csvFile();
    }

    private static void csvFile() {
        try {
            Reader in = new FileReader("C:\\Users\\bp180\\OneDrive\\finanace khatam_23\\send\\HDFC_23-24.csv");
            Iterable<CSVRecord> records = null;

            records = CSVFormat.EXCEL.parse(in);

            for (CSVRecord record : records) {
                for (int i = 0; i < record.size(); i++) {
                    System.out.print(record.get(i));
                    System.out.print("\t|||\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tabulaa() {
        File file = new File("C:\\Users\\bp180\\OneDrive\\finanace khatam_23\\Credit Card\\Credit_Kotak_23-24.pdf");
        try (PDDocument document = Loader.loadPDF(file)) {
            SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
            PageIterator pi = new ObjectExtractor(document).extract();
            while (pi.hasNext()) {
                // iterate over the pages of the document
                Page page = pi.next();
                List<Table> table = sea.extract(page);
                // iterate over the tables of the page
                for (Table tables : table) {
                    List<List<RectangularTextContainer>> rows = tables.getRows();
                    // iterate over the rows of the table
                    for (List<RectangularTextContainer> cells : rows) {
                        // print all column-cells of the row plus linefeed
                        for (RectangularTextContainer content : cells) {
                            // Note: Cell.getText() uses \r to concat text chunks
                            String text = content.getText().replace("\r", " ");
                            System.out.print(text + "|");
                        }
                        System.out.println();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void backup() {

        // Load a source PDF document which contains a table in it
        Document pdfDocument = new Document("C:\\Users\\bp180\\OneDrive\\finanace khatam_23\\SC_23-24.pdf");
        // Instantiate the TableAbsorber object for PDF tables extraction
        TableAbsorber tableAbsorber = new TableAbsorber();

        // visit the table collection in the input PDF
        tableAbsorber.visit(pdfDocument.getPages().get_Item(1));

        // Access the desired table from the tables collection
        List<AbsorbedTable> absorbedTableList = tableAbsorber.getTableList();

        for (AbsorbedTable absorbedTable : absorbedTableList) {
            // Parse all the rows and get each row using the AbsorbedRow
            for (AbsorbedRow pdfTableRow : absorbedTable.getRowList()) {
                // Access each cell in the cells collection using AbsorbedCell
                for (AbsorbedCell pdfTableCell : pdfTableRow.getCellList()) {
                    // Access each text fragment from the cell
                    TextFragmentCollection textFragmentCollection = pdfTableCell.getTextFragments();

                    // Access each text fragment from the fragments collection
                    for (com.aspose.pdf.TextFragment textFragment : textFragmentCollection) {
                        // Display the table cell text
                        System.out.print(textFragment.getText() + "\t|||||\t");
                    }

                    System.out.print("\nSSSSSSSSSSSSS\n");
                }

                System.out.print("\nMMMMMMMMMMM\n");
            }
            System.out.println("\nTTTTTTTTTTTT\n");
        }

        System.out.print("\n\n#######################\n\n");

    }

}
