package simple.com.simpleProject.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static Object[][] getExcelData(String filePath, String sheetName) {
        List<Object[]> data = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getLastCellNum();

            for (int i = 1; i < rowCount; i++) {  // start from 1 to skip header row
                Row row = sheet.getRow(i);
                Object[] rowData = new Object[colCount];
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        rowData[j] = cell.toString();  // Convert each cell to string
                    }
                }
                data.add(rowData);
            }

            workbook.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Convert the list to a 2D array
        return data.toArray(new Object[0][0]);
    }
}
