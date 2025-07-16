package com.Vilingo.controller;

import com.Vilingo.dto.TranslationResponse;
import com.Vilingo.dto.WordlistFetchInfoResponse;
import com.Vilingo.service.WordlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wordlist")
@RequiredArgsConstructor
public class WordlistController {

    private final WordlistService wordlistService;

    @PostMapping("/add")
    public ResponseEntity<Void> addWord(@RequestParam String word) {
        wordlistService.addWord(word);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteWord(@RequestParam String word) {
        wordlistService.deleteWord(word);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<WordlistFetchInfoResponse> getWordlist(
            @RequestParam int limit,
            @RequestParam(required = false) String before) {
        WordlistFetchInfoResponse response = wordlistService.getWordlist(before, limit);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/translate")
    public ResponseEntity<TranslationResponse> getTranslation(@RequestParam("name") String word) {
        return wordlistService.translateWord(word)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}