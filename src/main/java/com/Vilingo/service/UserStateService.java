package com.Vilingo.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 一个简单的服务，用于在内存中模拟用户状态的跟踪。
 * 它记录哪些练习ID已经被访问过。
 */
@Service
public class UserStateService {

    // 使用一个线程安全的Set来存储已访问过的练习ID
    private final Set<Integer> visitedExerciseIds = Collections.synchronizedSet(new HashSet<>());

    /**
     * 检查一个练习ID是否是第一次被访问。
     * 如果是第一次，它会记录下来，并返回 true。
     * 如果不是第一次，它直接返回 false。
     * @param exerciseId 要检查的练习ID
     * @return 如果是第一次访问，则为 true；否则为 false。
     */
    public boolean isFirstVisit(Integer exerciseId) {
        // Set.add() 方法有一个很有用的特性：
        // 如果元素成功添加（即之前不存在），它返回 true。
        // 如果元素已存在，添加失败，它返回 false。
        // 这正好完美地匹配了我们的需求！
        return visitedExerciseIds.add(exerciseId);
    }
}