package in.dhanab.avgraha.model.hdfc;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.pdf.AbsorbedCell;
import com.aspose.pdf.AbsorbedRow;
import com.aspose.pdf.AbsorbedTable;
import com.aspose.pdf.Document;
import com.aspose.pdf.TableAbsorber;
import com.aspose.pdf.TextFragment;
import com.aspose.pdf.TextFragmentCollection;

import in.dhanab.avgraha.collections.CreditCardStatement;
import in.dhanab.avgraha.model.base.CreditCardStatementExtractionStrategy;

public class HDFCCreditCardPDFExtract implements CreditCardStatementExtractionStrategy {


    @Override
    public CreditCardStatement extractStatementFromFile(MultipartFile statementFile) {

        CreditCardStatement creditCardStatement = new CreditCardStatement();

        try {
            Map<Integer, CreditCardStatement.CardTransaction> transactionMap = new HashMap<>();

            Document pdfDocument = new Document(statementFile.getInputStream());
            TableAbsorber tableAbsorber = new TableAbsorber();
            tableAbsorber.visit(pdfDocument.getPages().get_Item(1));

            List<AbsorbedTable> absorbedTableList = tableAbsorber.getTableList();
            AbsorbedTable absorbedTable = absorbedTableList.get(4);
            AbsorbedRow pdfTableRow = absorbedTable.getRowList().get(2);

            AbsorbedCell dateCell = pdfTableRow.getCellList().get(0);
            TextFragmentCollection dateTextCollection = dateCell.getTextFragments();
            Integer dateTransaction = 1;
            for (TextFragment textFragment : dateTextCollection) {
                if (StringUtils.isNotBlank(textFragment.getText())) {
                    String dateTime = textFragment.getText().trim().substring(0,10);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(dateTime, formatter);
                    transactionMap.put(dateTransaction, new CreditCardStatement.CardTransaction(date));
                    dateTransaction++;
                }
            }

            AbsorbedCell descriptionCell = pdfTableRow.getCellList().get(1);
            TextFragmentCollection descriptionTextCollection = descriptionCell.getTextFragments();
            Integer descriptionTransaction = 1;
            for (TextFragment textFragment : descriptionTextCollection) {
                if (StringUtils.isNotBlank(textFragment.getText()) && !textFragment.getText().contains("BHAVIK PARMAR")) {
                    CreditCardStatement.CardTransaction cardTransaction = transactionMap.get(descriptionTransaction);
                    cardTransaction.setDescription(StringUtils.normalizeSpace(textFragment.getText()));
                    descriptionTransaction++;
                }
            }

            AbsorbedCell amountCell = pdfTableRow.getCellList().get(2);
            TextFragmentCollection amountCellTextFragments = amountCell.getTextFragments();
            Integer amountTransaction = 1;
            for (TextFragment textFragment : amountCellTextFragments) {
                if (StringUtils.isNotBlank(textFragment.getText())) {
                    if (textFragment.getText().trim().contains("Cr")) {
                        CreditCardStatement.CardTransaction cardTransaction = transactionMap.get(amountTransaction-1);
                        cardTransaction.setAmount(cardTransaction.getAmount()*-1);
                    } else {
                        CreditCardStatement.CardTransaction cardTransaction = transactionMap.get(amountTransaction);
                        cardTransaction.setAmount(Float.parseFloat(textFragment.getText().trim().replace(",", "")));
                        amountTransaction++;
                    }
                }
            }

            System.out.println(transactionMap);

            creditCardStatement.setCardTransactionList(transactionMap.values().stream().toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return creditCardStatement;
    }
}
