package com.patrimoine.rattrapage.controller;

import com.patrimoine.rattrapage.controller.dto.PatrimoineDto;
import com.patrimoine.rattrapage.controller.mapper.PatrimoineMapper;
import com.patrimoine.rattrapage.dao.Patrimoine;
import com.patrimoine.rattrapage.service.PatrimoineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class PatrimoineController {
    private PatrimoineService patrimoineService;
    private PatrimoineMapper patrimoineMapper;

    @GetMapping("/patrimoines")
    public String p() {
        return "patrimoines";
    }

    @GetMapping("/patrimoines/{id}")
    public Patrimoine findPatrimoineById(@PathVariable String id) throws IOException {
        return this.patrimoineService.getPatrimoine(id);
    }

    @PutMapping("/patrimoines/{id}")
    public Patrimoine createOrUpdatePatrimoine(@PathVariable String id, @RequestBody PatrimoineDto patrimoine) throws IOException {
        return this.patrimoineService.saveOrUpdatePatrimoine(this.patrimoineMapper.toEntity(id, patrimoine));
    }
}
