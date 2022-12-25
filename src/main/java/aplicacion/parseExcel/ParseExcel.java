package aplicacion.parseExcel;

import java.io.*;
import java.time.ZoneId;
import java.util.*;

import org.jetbrains.annotations.NotNull;
import shared.helpers.Helpers;
import dominio.actividades.Factory.ParseErrorException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParseExcel {
  public Map<Integer, List<String>> leerExcelFromBytes(InputStream input) {
    Workbook workbook;
    try {
      workbook = new XSSFWorkbook(input);
    } catch (Exception e) {
      throw new ParseErrorException("No se pudo leer el archivo");
    }
    return leerExcel(workbook);
  }

  public Map<Integer, List<String>> leerExcelDesdeRuta(String path) {
    Workbook workbook;
    FileInputStream file;
    try {
      file = new FileInputStream(path);
      workbook = new XSSFWorkbook(file);
    } catch (Exception e) {
      throw new ParseErrorException("No se pudo leer el archivo");
    }
    return leerExcel(workbook);
  }

  private Map<Integer, List<String>> leerExcel(Workbook workbook)  {
    Sheet sheet = workbook.getSheetAt(0);

    Map<Integer, List<String>> data = new HashMap<>();
    Integer rowIndex = 0;

    for (Row row : sheet) {
      for (Cell cell : row) {
        String value = "";
        switch (cell.getCellType()) {
          case STRING:
            value = cell.getStringCellValue();
            break;

          case NUMERIC: {
            if (DateUtil.isCellDateFormatted(cell)) {
              value = cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
            } else {
              value = String.valueOf(cell.getNumericCellValue());
            }
          }
          break;

          case BLANK: continue;

          default:
            throw new ParseErrorException();
        }
        if (!Helpers.isNullOrEmpty(value)) {
          if (data.get(rowIndex) == null) {
            data.put(rowIndex, new ArrayList<String>());
          }
          data.get(rowIndex).add(value);
        }
      }
      rowIndex++;
    }
    return data;
  }
}
