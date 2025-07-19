package com.Vilingo.service;

import com.Vilingo.dto.RandomWordResponse;
import com.Vilingo.entity.WordEntry;
import com.Vilingo.dto.TranslationResponse;
import com.Vilingo.dto.WordlistFetchInfoResponse;
import com.Vilingo.repository.WordlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordlistService {

    private final WordlistRepository wordlistRepository;
    private final TranslationService translationService;
    private final StaticContentService staticContentService;

    public void addWord(String word) {
        // 防止添加重复单词
        if (wordlistRepository.existsByWord(word)) {
            return;
        }
        WordEntry newEntry = new WordEntry();
        newEntry.setWord(word);
        wordlistRepository.save(newEntry);
    }

    @Transactional
    public void deleteWord(String word) {
        if (!wordlistRepository.existsByWord(word)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Word not found in wordlist");
        }
        wordlistRepository.deleteByWord(word);
    }

    // 在 WordlistService.java 中

    public WordlistFetchInfoResponse getWordlist(String before, int limit) {
        // 1. 请求比期望多一条数据，用以判断是否还有下一页
        PageRequest pageable = PageRequest.of(0, limit + 1);
        Page<WordEntry> page;

        if (before == null || before.isEmpty()) {
            page = wordlistRepository.findAllByOrderByWordAsc(pageable);
        } else {
            page = wordlistRepository.findByWordGreaterThanOrderByWordAsc(before, pageable);
        }

        // 2. 获取查询到的所有单词实体
        List<WordEntry> entries = page.getContent();

        // 3. 判断是否真的有下一页
        boolean hasNextPage = entries.size() > limit;

        // 4. 如果有下一页，只截取 limit 数量的单词返回给前端
        List<WordEntry> wordsToReturn = hasNextPage ? entries.subList(0, limit) : entries;

        // 5. 将实体列表转换为字符串列表
        List<String> wordStrings = wordsToReturn.stream()
                .map(WordEntry::getWord)
                .toList();

        // 6. 确定 "nextWord" (即当前页的最后一个单词)，用于下一次请求的 "before" 参数
        String nextWord = wordStrings.isEmpty() ? null : wordStrings.get(wordStrings.size() - 1);

        // 7. 构建并返回最终的响应对象
        return new WordlistFetchInfoResponse(wordStrings.size(), wordStrings, hasNextPage, nextWord);
    }

    public Optional<TranslationResponse> translateWord(String word) {
        // 关键修改：使用 Optional.ofNullable() 来包装可能为 null 的返回值
        // 如果 translationService.getTranslation(word) 返回 null，它会变成 Optional.empty()
        // 如果返回一个 Optional 对象，它会保持原样
        return Optional.ofNullable(translationService.getTranslation(word))
                .orElse(Optional.empty());
    }

    /**
     * 从数据库的 word_entries 表中随机获取四个不重复的单词。
     * @return 如果数据库中单词数量足够，则返回包含四个单词的DTO；否则返回空。
     */
    public Optional<RandomWordResponse> getRandomWords() {
        // 1. 检查数据库中的单词总数是否足够
        long totalWords = wordlistRepository.countWords();
        if (totalWords < 4) {
            return Optional.empty(); // 如果不够4个，直接返回空
        }

        // 2. 调用 Repository 中我们自定义的随机查询方法
        List<WordEntry> randomEntries = wordlistRepository.findRandomWords(4);

        // 3. 将 WordEntry 实体列表转换为 String 列表
        List<String> randomWords = randomEntries.stream()
                .map(WordEntry::getWord)
                .toList();

        // 4. 再次确认是否真的获取到了4个单词 (防御性编程)
        if (randomWords.size() < 4) {
            return Optional.empty();
        }

        // 5. 将这四个单词封装到 DTO 中
        RandomWordResponse response = new RandomWordResponse(
                randomWords.get(0),
                randomWords.get(1),
                randomWords.get(2),
                randomWords.get(3)
        );

        // 6. 将结果包装在 Optional 中返回
        return Optional.of(response);
    }
}