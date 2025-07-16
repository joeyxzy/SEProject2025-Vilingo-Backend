package com.Vilingo.repository; // 请替换为你的包名

import com.Vilingo.entity.WordEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordlistRepository extends JpaRepository<WordEntry, Long> {

    // 根据单词检查是否存在
    boolean existsByWord(String word);

    // 根据单词删除条目 (需要事务支持)
    void deleteByWord(String word);

    // --- 用于 /wordlist/get 分页查询 ---

    // 获取第一页数据 (当 before 参数为空时)
    Page<WordEntry> findAllByOrderByWordAsc(Pageable pageable);

    // 获取后续页数据 (当 before 参数不为空时)
    // 查询所有按字母顺序大于 "before" 参数的单词
    Page<WordEntry> findByWordGreaterThanOrderByWordAsc(String before, Pageable pageable);
}