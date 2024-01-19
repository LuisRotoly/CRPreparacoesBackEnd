package com.crpreparacoes.controllers;

import com.crpreparacoes.dto.ReportDTO;
import com.crpreparacoes.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**Método para buscar o relatório de recebidos
     * @return List<Double> - Lista de recebiveis do ano
     */
    @RequestMapping(value="/getReportData", method = RequestMethod.GET)
    public @ResponseBody List<Double> getReportData(@RequestParam String year){
        return reportService.getReportData(year);
    }
}
