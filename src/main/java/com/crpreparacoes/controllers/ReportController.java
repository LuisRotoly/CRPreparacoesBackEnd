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

    /**Método para buscar o relatório de recebidos brutos
     * @return List<Double> - Lista de recebiveis brutos do ano
     */
    @RequestMapping(value="/getGrossIncomeData", method = RequestMethod.GET)
    public @ResponseBody List<Double> getGrossIncomeData(@RequestParam String year){
        return reportService.getGrossIncomeData(year);
    }

    /**Método para buscar o relatório de recebidos líquidos
     * @return List<Double> - Lista de recebiveis líquidos do ano
     */
    @RequestMapping(value="/getNetRevenueData", method = RequestMethod.GET)
    public @ResponseBody List<Double> getNetRevenueData(@RequestParam String year){
        return reportService.getNetRevenueData(year);
    }

    /**Método para buscar o valor gasto com peças
     * @return List<Double> - Lista com o valor gasto com peças do ano
     */
    @RequestMapping(value="/getBikePartSpentData", method = RequestMethod.GET)
    public @ResponseBody List<Double> getBikePartSpentData(@RequestParam String year){
        return reportService.getBikePartSpentData(year);
    }
}
