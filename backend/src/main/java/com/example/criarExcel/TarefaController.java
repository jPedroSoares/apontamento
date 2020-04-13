package com.example.criarExcel;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    private static String filename = "C:/Users/joaos/OneDrive/Documentos/Bandtec/Work/Apontamento/3 - JOAO_09-13_DE_MARCO_APONTAMENTOV3.xlsx";
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    static int linha = 4;

    @PostMapping(value="/teste")
    public Tarefa teste(@RequestBody Tarefa tarefa) {
        return tarefa;
    }

    @PostMapping(value = "/finalizar")
    public  String finalizar(){

        Date date = new Date();
        String horaFim = hourFormat.format(date);

        try(InputStream arquivo = new FileInputStream(filename)) {

            Workbook wb = WorkbookFactory.create(arquivo);
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(linha);

            while (!row.getCell(1).getStringCellValue().equals("")){
                linha++;
                row = sheet.getRow(linha);
            }
            row = sheet.getRow(linha-1);
            row.getCell(4).setCellValue(horaFim);

            try (OutputStream fileOut = new FileOutputStream(filename)) {
                wb.write(fileOut);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Dia finalizado com sucesso";

    }

    @PostMapping(value="/adicionar")
        public Tarefa adicionar(@RequestBody Tarefa tarefa){
        Date date = new Date();

        tarefa.setData(dateFormat.format(date));
        tarefa.setHoraInicio(hourFormat.format(date));

        try(InputStream arquivo = new FileInputStream(filename)) {

            Workbook wb = WorkbookFactory.create(arquivo);
            Sheet sheet = wb.getSheetAt(0);

            escrever(tarefa, wb, sheet);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tarefa;
    }
    public static void escrever(Tarefa tarefa, Workbook wb, Sheet sheet){

        Row base = sheet.getRow(linha);

        while (!base.getCell(1).getStringCellValue().equals("")) {
            linha++;
            base = sheet.getRow(linha);
        }
        Row row = sheet.getRow(linha);
        Row rowAnt = sheet.getRow(linha-1);
        try {
            if(rowAnt.getCell(4).getNumericCellValue() == 0.0)
                rowAnt.getCell(4).setCellValue(tarefa.getHoraInicio());
        }catch (Exception e){
            if(rowAnt.getCell(4).getStringCellValue().equals(""))
                rowAnt.getCell(4).setCellValue(tarefa.getHoraInicio());
        }

        Cell cells[] = {row.getCell(1), row.getCell(2), row.getCell(3), row.getCell(6), row.getCell(7),
                row.getCell(8), row.getCell(9)};

        cells[0].setCellValue(tarefa.getNome());
        cells[1].setCellValue(tarefa.getData());
        cells[2].setCellValue(tarefa.getHoraInicio());
        cells[3].setCellValue(tarefa.getTipoAtividade());
        cells[4].setCellValue(tarefa.getNomeProjeto());
        cells[5].setCellValue(tarefa.getCliente());
        cells[6].setCellValue(tarefa.getDescricao());
        linha++;

        try (OutputStream fileOut = new FileOutputStream(filename)) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
