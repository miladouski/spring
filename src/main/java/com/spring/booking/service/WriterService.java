package com.spring.booking.service;


import com.spring.booking.exceptions.FileWriteException;
import com.spring.booking.models.Account;
import com.spring.booking.models.Order;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.spring.booking.constants.ServiceConstants.*;

@Service
public class WriterService {

    private final AccountService accountService;
    private final OrderService orderService;

    public WriterService(AccountService accountService, OrderService orderService) {
        this.accountService = accountService;
        this.orderService = orderService;
    }

    public void writeTable() {
        Workbook workbook = new HSSFWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(TABLE_DATE_FORMAT));
        Sheet sheet = workbook.createSheet();
        int firstRow;
        int lastRow = 0;
        int rowCount;

        List<Account> accounts = accountService.getAllAccounts();
        for (Account account : accounts) {
            firstRow = lastRow;
            rowCount = firstRow;
            Row row = sheet.createRow(rowCount++);
            Cell cell = row.createCell(0);
            cell.setCellValue(account.getUsername());
            cell = row.createCell(1);
            cell.setCellValue(account.getEmail());
            cell = row.createCell(2);
            cell.setCellValue("From:");
            cell = row.createCell(3);
            cell.setCellValue("To:");
            cell = row.createCell(4);
            cell.setCellValue("Time:");
            cell = row.createCell(5);
            cell.setCellValue("Amount:");
            cell = row.createCell(6);
            cell.setCellValue("Date");
            lastRow = writeAccountOrders(account, sheet, rowCount, cellStyle);
            if(lastRow - firstRow != 1) {
                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow - 1, 0, 0));
                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow - 1, 1, 1));
            }
            lastRow++;
        }
        for(int i = 0; i <= 6; i++) {
            sheet.autoSizeColumn(i);
        }
        sheet.setColumnWidth(4, sheet.getColumnWidth(4) + 1000);
        sheet.setColumnWidth(6, sheet.getColumnWidth(6) + 1000);

        try (FileOutputStream outputStream = new FileOutputStream(FILE_PATH)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new FileWriteException("File not available");
        }
    }

    private int writeAccountOrders(Account account, Sheet sheet, int rowCount, CellStyle cellStyle) {
        List<Order> orders = orderService.getAccountOrders(account);
        for (Order order : orders) {
            Row row = sheet.createRow(rowCount++);
            Cell cell = row.createCell(2);
            cell.setCellValue(order.getTicket().getRoute().getStartPoint());
            cell = row.createCell(3);
            cell.setCellValue(order.getTicket().getRoute().getEndPoint());
            cell = row.createCell(4);
            cell.setCellValue(order.getTicket().getDate());
            cell.setCellStyle(cellStyle);
            cell = row.createCell(5);
            cell.setCellValue(order.getAmount());
            cell = row.createCell(6);
            cell.setCellValue(order.getDate());
            cell.setCellStyle(cellStyle);
        }
        return rowCount;
    }

    public Resource loadAsResource() {
        Resource resource = new FileSystemResource(FILE_PATH);
        return resource;
    }

}
