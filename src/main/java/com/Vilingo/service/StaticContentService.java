package com.Vilingo.service;

import com.Vilingo.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StaticContentService {

    // 新增一个Map来存储所有练习数据
    private final Map<Integer, ExerciseInfoResponse> exercises;

    // 修改构造函数来初始化练习数据
    public StaticContentService() {
        // ========== 1. 初始化练习数据 ==========
        this.exercises = initializeExercises();

        // ========== 2. 初始化章节数据 (这部分代码保持不变) ==========
        // ... (你原来的 getFullContent() 的所有逻辑)
    }

    // 新增一个私有方法来创建所有练习的静态数据
    private Map<Integer, ExerciseInfoResponse> initializeExercises() {
        // 为练习 101 创建数据
        VideoExerciseResponse item101 = new VideoExerciseResponse();
        item101.setId(1011); // 练习项的ID
        //item101.setType("video");
        item101.setVideoKey("videos/1011video.mp4"); // 在OSS中的视频路径
        item101.setSrtKey("srts/1011srt.srt"); // 在OSS中的字幕路径

        ExerciseInfoResponse exercise101 = new ExerciseInfoResponse();
        exercise101.setXp(10);
        exercise101.setDifficulty("easy");
        exercise101.setItems(List.of(item101));

        // 为练习 102 创建数据
        VideoExerciseResponse item102 = new VideoExerciseResponse();
        item102.setId(1021);
        //item102.setType("video");
        item102.setVideoKey("videos/self-introduction.mp4");
        item102.setSrtKey("subtitles/self-introduction.srt");

        ExerciseInfoResponse exercise102 = new ExerciseInfoResponse();
        exercise102.setXp(15);
        exercise102.setDifficulty("easy");
        exercise102.setItems(List.of(item102));

        // 为练习 109 (餐厅点餐) 创建数据
        VideoExerciseResponse item109 = new VideoExerciseResponse();
        item109.setId(1091);
        //item109.setType("video");
        item109.setVideoKey("videos/ordering-food.mp4");
        item109.setSrtKey("subtitles/ordering-food.srt");

        ExerciseInfoResponse exercise109 = new ExerciseInfoResponse();
        exercise109.setXp(20);
        exercise109.setDifficulty("medium");
        exercise109.setItems(List.of(item109));

        // 将所有练习放入Map中，Key是练习ID
        return Map.of(
                101, exercise101,
                102, exercise102,
                109, exercise109
                // 你可以按照这个模式，为之前创建的所有16个练习ID添加数据
        );
    }

    // 新增一个公共方法，用于根据ID查找练习
    public ExerciseInfoResponse findExerciseById(Integer id) {
        return this.exercises.get(id);
    }

    /**
     * Gets the complete, static list of sections.
     * The content has been enriched and translated into English.
     * @return A list containing all section information.
     */
    public List<SectionInfoResponse> getEnFullContent() {

        // =======================================================================
        // SECTION 1: "Everyday Conversation Basics"
        // =======================================================================
        List<ChapterInfoResponse> section1Chapters = new ArrayList<>();
        int exerciseIdCounter = 100; // Exercise IDs start from 100 to ensure uniqueness

        // --- Chapter 1.1: Greetings & Introductions ---
        LessonInfoResponse lesson1_1_1 = new LessonInfoResponse(++exerciseIdCounter, "First Encounters: 'Hello' and 'Goodbye'", List.of(exerciseIdCounter));
        LessonInfoResponse lesson1_1_2 = new LessonInfoResponse(++exerciseIdCounter, "Introducing Yourself: 'My name is...'", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter1_1 = new ChapterInfoResponse(11, "Greetings & Introductions", "Learn how to start and end a conversation.", List.of(lesson1_1_1, lesson1_1_2));
        section1Chapters.add(chapter1_1);

        // --- Chapter 1.2: Gratitude & Apologies ---
        LessonInfoResponse lesson1_2_1 = new LessonInfoResponse(++exerciseIdCounter, "Common Courtesies: 'Thank You!'", List.of(exerciseIdCounter));
        LessonInfoResponse lesson1_2_2 = new LessonInfoResponse(++exerciseIdCounter, "Polite Apologies: 'I'm sorry.'", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter1_2 = new ChapterInfoResponse(12, "Gratitude & Apologies", "Master polite expressions to improve communication.", List.of(lesson1_2_1, lesson1_2_2));
        section1Chapters.add(chapter1_2);

        // --- Chapter 1.3: Asking for Information ---
        LessonInfoResponse lesson1_3_1 = new LessonInfoResponse(++exerciseIdCounter, "Asking for Directions: 'Where is...?'", List.of(exerciseIdCounter));
        LessonInfoResponse lesson1_3_2 = new LessonInfoResponse(++exerciseIdCounter, "Telling Time: 'What time is it?'", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter1_3 = new ChapterInfoResponse(13, "Asking for Information", "Learn how to get the basic information you need.", List.of(lesson1_3_1, lesson1_3_2));
        section1Chapters.add(chapter1_3);

        // --- Chapter 1.4: Shopping Dialogues ---
        LessonInfoResponse lesson1_4_1 = new LessonInfoResponse(++exerciseIdCounter, "Inquiring about Price: 'How much is this?'", List.of(exerciseIdCounter));
        LessonInfoResponse lesson1_4_2 = new LessonInfoResponse(++exerciseIdCounter, "Simple Bargaining: 'It's too expensive!'", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter1_4 = new ChapterInfoResponse(14, "Shopping Dialogues", "Master basic conversations for shopping in a store.", List.of(lesson1_4_1, lesson1_4_2));
        section1Chapters.add(chapter1_4);

        // Create the first Section object
        SectionInfoResponse section1 = new SectionInfoResponse(1, "Unit 1: Everyday Conversation Basics", section1Chapters);


        // =======================================================================
        // SECTION 2: "Life & Travel"
        // =======================================================================
        List<ChapterInfoResponse> section2Chapters = new ArrayList<>();
        // exerciseIdCounter continues from where the last section left off

        // --- Chapter 2.1: Ordering at a Restaurant ---
        LessonInfoResponse lesson2_1_1 = new LessonInfoResponse(++exerciseIdCounter, "Reading the Menu and Ordering", List.of(exerciseIdCounter));
        LessonInfoResponse lesson2_1_2 = new LessonInfoResponse(++exerciseIdCounter, "Paying the Bill: 'Check, please!'", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter2_1 = new ChapterInfoResponse(21, "Ordering at a Restaurant", "Confidently enjoy a meal at any restaurant.", List.of(lesson2_1_1, lesson2_1_2));
        section2Chapters.add(chapter2_1);

        // --- Chapter 2.2: Transportation ---
        LessonInfoResponse lesson2_2_1 = new LessonInfoResponse(++exerciseIdCounter, "Taking a Taxi", List.of(exerciseIdCounter));
        LessonInfoResponse lesson2_2_2 = new LessonInfoResponse(++exerciseIdCounter, "Buying a Subway Ticket", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter2_2 = new ChapterInfoResponse(22, "Transportation", "Learn how to use public transport.", List.of(lesson2_2_1, lesson2_2_2));
        section2Chapters.add(chapter2_2);

        // --- Chapter 2.3: Talking about the Weather ---
        LessonInfoResponse lesson2_3_1 = new LessonInfoResponse(++exerciseIdCounter, "Describing the Weather: 'It's sunny/rainy.'", List.of(exerciseIdCounter));
        LessonInfoResponse lesson2_3_2 = new LessonInfoResponse(++exerciseIdCounter, "Asking for the Forecast", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter2_3 = new ChapterInfoResponse(23, "Talking about the Weather", "Use the weather as an easy conversation starter.", List.of(lesson2_3_1, lesson2_3_2));
        section2Chapters.add(chapter2_3);

        // --- Chapter 2.4: Booking Accommodation ---
        LessonInfoResponse lesson2_4_1 = new LessonInfoResponse(++exerciseIdCounter, "Booking a Hotel Room", List.of(exerciseIdCounter));
        LessonInfoResponse lesson2_4_2 = new LessonInfoResponse(++exerciseIdCounter, "Checking In and Checking Out", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter2_4 = new ChapterInfoResponse(24, "Booking Accommodation", "Handle your lodging needs while traveling.", List.of(lesson2_4_1, lesson2_4_2));
        section2Chapters.add(chapter2_4);

        // Create the second Section object
        SectionInfoResponse section2 = new SectionInfoResponse(2, "Unit 2: Life & Travel", section2Chapters);

        // =======================================================================
        // Return all Sections
        // =======================================================================
        return List.of(section1, section2);
    }
}