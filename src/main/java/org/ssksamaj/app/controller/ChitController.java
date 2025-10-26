package org.ssksamaj.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssksamaj.app.dto.ChitDTO;
import org.ssksamaj.app.service.ChitService;

@RestController
@RequestMapping("/chit")
public class ChitController {

    private final ChitService chitService;

    public ChitController(ChitService chitService) {
        this.chitService = chitService;
    }

    @GetMapping("/new")
    public ResponseEntity<ChitDTO> newChit() {
        ChitDTO chitDTO = ChitDTO.builder()
                .build();
        return ResponseEntity.ok(chitDTO);
    }

    @GetMapping("/{chitId}")
    public ResponseEntity<ChitDTO> getChitDetails(Integer chitId) {
        return ResponseEntity.ok(chitService.getChitDetails(chitId));
    }

    @PostMapping("/create")
    public ResponseEntity<ChitDTO> createChit(ChitDTO chitDTO){
        return ResponseEntity.ok(chitDTO);
    }
}
