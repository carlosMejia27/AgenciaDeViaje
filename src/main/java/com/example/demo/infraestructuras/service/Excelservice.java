package com.example.demo.infraestructuras.service;

import com.example.demo.dominan.entities.jpa.Customer;
import com.example.demo.dominan.repository.jpa.CustomerRepository;
import com.example.demo.infraestructuras.abstract_service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;


@Service
@Slf4j
@AllArgsConstructor
public class Excelservice implements ReportService {

    private static final String SHEET_NAME = "Customer total sales";
    private static final String FONT_TYPE = "Arial";
    private static final String COLUMN_CUSTOMER_ID = "id";
    private static final String COLUMN_CUSTOMER_NAME = "name";
    private static final String COLUMN_CUSTOMER_PURCHASES = "purchases";
    private static final String REPORTS_PATH_WITH_NAME = "reports/Sales-%s";
    private static final String REPORTS_PATH = "reports";
    private static final String FILE_TYPE = ".xlsx";
    private static final String FILE_NAME = "Sales-%s.xlsx";

    CustomerRepository customerRepository;

    @Override
    public byte[] readFile() {

        try {
            this.createReport();
            var path = Paths.get(REPORTS_PATH, String.format(FILE_NAME, LocalDate.now().getMonth())).toAbsolutePath();
            return Files.readAllBytes(path);

        } catch (IOException e) {
            log.error("no se creo el excel", e);
            throw new RuntimeException();
        }
    }

    private void createReport() {
        var workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet(SHEET_NAME);

        sheet.setColumnWidth(0, 7000);// columnas
        sheet.setColumnWidth(1, 7000);
        sheet.setColumnWidth(2, 7000);

        var header = sheet.createRow(0);// filas la cabecera
        var headerstyle = workbook.createCellStyle();// agrego propiedades como color
        headerstyle.setFillForegroundColor(IndexedColors.VIOLET.getIndex());
        headerstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        var fonts = workbook.createFont();
        fonts.setFontName(FONT_TYPE);
        fonts.setFontHeightInPoints((short) 14);
        fonts.setBold(true);
        // agrego al header
        headerstyle.setFont(fonts);

        var headercell = header.createCell(0);
        headercell.setCellValue(COLUMN_CUSTOMER_ID);
        headercell.setCellStyle(headerstyle);

        headercell = header.createCell(1);
        headercell.setCellValue(COLUMN_CUSTOMER_NAME);
        headercell.setCellStyle(headerstyle);

        headercell = header.createCell(2);
        headercell.setCellValue(COLUMN_CUSTOMER_PURCHASES);
        headercell.setCellStyle(headerstyle);

        //lo de abajo de las cabeceras
        var style = workbook.createCellStyle();
        style.setWrapText(true);

        /// traigo todos los valores de la bbdd
        var customers = this.customerRepository.findAll();
        var rowPos = 1; // porque la cero ya esta escojida

        // recorremos los datos de la bbdd
        for (Customer Customer : customers) {
            var row = sheet.createRow(rowPos);

            //---------------1---------------------//
            var cell = row.createCell(0);
            cell.setCellValue(Customer.getDni());
            cell.setCellStyle(style);

            //---------------2---------------------//
            cell = row.createCell(1);
            cell.setCellValue(Customer.getFullName());
            cell.setCellStyle(style);

            //---------------3---------------------//

            cell = row.createCell(2);
            cell.setCellValue(getTotalPurchase(Customer));
            cell.setCellStyle(style);
            rowPos++;

        }

        var report = new File(String.format(REPORTS_PATH_WITH_NAME, LocalDate.now().getMonth()));
        var path = report.getAbsolutePath();
        var fileLocation = path + FILE_TYPE;

        try (var outputStream = new FileOutputStream(fileLocation)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            log.error("no se creo el excel", e);
            throw new RuntimeException();
        }
    }

    private static int getTotalPurchase(Customer customer) {
        return customer.getTotalLodgings() + customer.getTotalFlights() + customer.getTotalTours();
    }
}
