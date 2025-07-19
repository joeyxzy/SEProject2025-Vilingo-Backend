package com.Vilingo.repository; // 请替换为你的包名

import com.Vilingo.entity.WordEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 从 word_entries 表中随机获取指定数量的单词。
     * 这个查询利用了数据库原生的 RANDOM() 函数，并在数据量大时依然有不错的性能。
     *
     * @param count 需要获取的随机单词数量
     * @return 一个包含随机 WordEntry 实体的列表
     */
    @Query(value = "SELECT * FROM word_entries ORDER BY RANDOM() LIMIT :count", nativeQuery = true)
    List<WordEntry> findRandomWords(@Param("count") int count);

    /**
     * 获取表中的总记录数。
     * @return 表中的单词总数
     */
    @Query(value = "SELECT count(*) FROM word_entries", nativeQuery = true)
    long countWords();
}