package com.Vilingo.service; // 请替换为你的包名

import com.Vilingo.dto.*;
import org.springframework.stereotype.Service;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaticContentService {

    private final Map<Integer, ExerciseInfoResponse> exercises;
    @Getter
    private final List<RecommendVideoResponse> recommendedVideos;

    public StaticContentService() {
        this.exercises = initializeExercises();
        this.recommendedVideos = initializeRecommendedVideos();
    }

    private Map<Integer, ExerciseInfoResponse> initializeExercises() {
        // 使用 HashMap 和 put，比超长的 Map.of() 更清晰
        Map<Integer, ExerciseInfoResponse> exerciseMap = new HashMap<>();

        // --- 练习 101 ---
        VideoExerciseResponse item101 = new VideoExerciseResponse();
        item101.setId(1011);
        item101.setVideoKey("video/1011video.mp4");
        item101.setSrtKey("srt/1011srt.srt");
        ExerciseInfoResponse exercise101 = new ExerciseInfoResponse();
        exercise101.setXp(10);
        exercise101.setDifficulty("easy");
        exercise101.setItems(List.of(item101));
        exerciseMap.put(101, exercise101);

        // --- 练习 102 ---
        VideoExerciseResponse item102 = new VideoExerciseResponse();
        item102.setId(1021);
        item102.setVideoKey("video/1021video.mp4");
        item102.setSrtKey("srt/1021srt.srt");
        ExerciseInfoResponse exercise102 = new ExerciseInfoResponse();
        exercise102.setXp(15);
        exercise102.setDifficulty("easy");
        exercise102.setItems(List.of(item102));
        exerciseMap.put(102, exercise102);

        // --- 练习 103 (新增) ---
        VideoExerciseResponse item103 = new VideoExerciseResponse();
        item103.setId(1031);
        item103.setVideoKey("video/1031video.mp4");
        item103.setSrtKey("srt/1031srt.srt");
        ExerciseInfoResponse exercise103 = new ExerciseInfoResponse();
        exercise103.setXp(15);
        exercise103.setDifficulty("easy");
        exercise103.setItems(List.of(item103));
        exerciseMap.put(103, exercise103);

        // --- 练习 104 (新增) ---
        VideoExerciseResponse item104 = new VideoExerciseResponse();
        item104.setId(1041);
        item104.setVideoKey("video/1041video.mp4");
        item104.setSrtKey("srt/1041srt.srt");
        ExerciseInfoResponse exercise104 = new ExerciseInfoResponse();
        exercise104.setXp(18);
        exercise104.setDifficulty("easy");
        exercise104.setItems(List.of(item104));
        exerciseMap.put(104, exercise104);

        // --- 练习 105 (新增) ---
        VideoExerciseResponse item105 = new VideoExerciseResponse();
        item105.setId(1051);
        item105.setVideoKey("video/1051video.mp4");
        item105.setSrtKey("srt/1051srt.srt");
        ExerciseInfoResponse exercise105 = new ExerciseInfoResponse();
        exercise105.setXp(20);
        exercise105.setDifficulty("medium"); // 难度提升
        exercise105.setItems(List.of(item105));
        exerciseMap.put(105, exercise105);

        // --- 练习 106 (新增) ---
        VideoExerciseResponse item106 = new VideoExerciseResponse();
        item106.setId(1061);
        item106.setVideoKey("video/1061video.mp4");
        item106.setSrtKey("srt/1061srt.srt");
        ExerciseInfoResponse exercise106 = new ExerciseInfoResponse();
        exercise106.setXp(20);
        exercise106.setDifficulty("medium");
        exercise106.setItems(List.of(item106));
        exerciseMap.put(106, exercise106);

        // --- 练习 107 (新增) ---
        VideoExerciseResponse item107 = new VideoExerciseResponse();
        item107.setId(1071);
        item107.setVideoKey("video/1071video.mp4");
        item107.setSrtKey("srt/1071srt.srt");
        ExerciseInfoResponse exercise107 = new ExerciseInfoResponse();
        exercise107.setXp(22);
        exercise107.setDifficulty("medium");
        exercise107.setItems(List.of(item107));
        exerciseMap.put(107, exercise107);

        // --- 练习 108 (新增) ---
        VideoExerciseResponse item108 = new VideoExerciseResponse();
        item108.setId(1081);
        item108.setVideoKey("video/1081video.mp4");
        item108.setSrtKey("srt/1081srt.srt");
        ExerciseInfoResponse exercise108 = new ExerciseInfoResponse();
        exercise108.setXp(25);
        exercise108.setDifficulty("hard"); // 难度提升
        exercise108.setItems(List.of(item108));
        exerciseMap.put(108, exercise108);

        // --- 练习 109 ---
        VideoExerciseResponse item109 = new VideoExerciseResponse();
        item109.setId(1091);
        item109.setVideoKey("video/1091video.mp4");
        item109.setSrtKey("srt/1091srt.srt");
        ExerciseInfoResponse exercise109 = new ExerciseInfoResponse();
        exercise109.setXp(20);
        exercise109.setDifficulty("medium");
        exercise109.setItems(List.of(item109));
        exerciseMap.put(109, exercise109);

        return exerciseMap;
    }

    public ExerciseInfoResponse findExerciseById(Integer id) {
        return this.exercises.get(id);
    }

    /**
     * Gets the complete, static list of sections.
     * All chapters from Unit 1 and Unit 2 have been merged into a single section.
     * @return A list containing a single, comprehensive section.
     */
    public List<SectionInfoResponse> getEnFullContent() {

        // 创建一个列表来容纳所有的 Chapter
        List<ChapterInfoResponse> allChapters = new ArrayList<>();
        int exerciseIdCounter = 100;

        // =======================================================================
        // PART 1: "Everyday Conversation Basics" Chapters
        // =======================================================================
        // --- Chapter 1.1: Greetings & Introductions ---
        LessonInfoResponse lesson1_1_1 = new LessonInfoResponse(++exerciseIdCounter, "First Encounters: 'Hello' and 'Goodbye'", List.of(exerciseIdCounter));
        LessonInfoResponse lesson1_1_2 = new LessonInfoResponse(++exerciseIdCounter, "Introducing Yourself: 'My name is...'", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter1_1 = new ChapterInfoResponse(11, "Greetings & Introductions", "Learn how to start and end a conversation.", List.of(lesson1_1_1, lesson1_1_2));
        allChapters.add(chapter1_1);

        // --- Chapter 1.2: Gratitude & Apologies ---
        LessonInfoResponse lesson1_2_1 = new LessonInfoResponse(++exerciseIdCounter, "Common Courtesies: 'Thank You!'", List.of(exerciseIdCounter));
        LessonInfoResponse lesson1_2_2 = new LessonInfoResponse(++exerciseIdCounter, "Polite Apologies: 'I'm sorry.'", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter1_2 = new ChapterInfoResponse(12, "Gratitude & Apologies", "Master polite expressions to improve communication.", List.of(lesson1_2_1, lesson1_2_2));
        allChapters.add(chapter1_2);

        // --- Chapter 1.3: Asking for Information ---
        LessonInfoResponse lesson1_3_1 = new LessonInfoResponse(++exerciseIdCounter, "Asking for Directions: 'Where is...?'", List.of(exerciseIdCounter));
        LessonInfoResponse lesson1_3_2 = new LessonInfoResponse(++exerciseIdCounter, "Telling Time: 'What time is it?'", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter1_3 = new ChapterInfoResponse(13, "Asking for Information", "Learn how to get the basic information you need.", List.of(lesson1_3_1, lesson1_3_2));
        allChapters.add(chapter1_3);

        // --- Chapter 1.4: Shopping Dialogues ---
        LessonInfoResponse lesson1_4_1 = new LessonInfoResponse(++exerciseIdCounter, "Inquiring about Price: 'How much is this?'", List.of(exerciseIdCounter));
        LessonInfoResponse lesson1_4_2 = new LessonInfoResponse(++exerciseIdCounter, "Simple Bargaining: 'It's too expensive!'", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter1_4 = new ChapterInfoResponse(14, "Shopping Dialogues", "Master basic conversations for shopping in a store.", List.of(lesson1_4_1, lesson1_4_2));
        allChapters.add(chapter1_4);

        // =======================================================================
        // PART 2: "Life & Travel" Chapters
        // =======================================================================
        // --- Chapter 2.1: Ordering at a Restaurant ---
        LessonInfoResponse lesson2_1_1 = new LessonInfoResponse(++exerciseIdCounter, "Reading the Menu and Ordering", List.of(exerciseIdCounter));
        LessonInfoResponse lesson2_1_2 = new LessonInfoResponse(++exerciseIdCounter, "Paying the Bill: 'Check, please!'", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter2_1 = new ChapterInfoResponse(21, "Ordering at a Restaurant", "Confidently enjoy a meal at any restaurant.", List.of(lesson2_1_1, lesson2_1_2));
        allChapters.add(chapter2_1);

        // --- Chapter 2.2: Transportation ---
        LessonInfoResponse lesson2_2_1 = new LessonInfoResponse(++exerciseIdCounter, "Taking a Taxi", List.of(exerciseIdCounter));
        LessonInfoResponse lesson2_2_2 = new LessonInfoResponse(++exerciseIdCounter, "Buying a Subway Ticket", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter2_2 = new ChapterInfoResponse(22, "Transportation", "Learn how to use public transport.", List.of(lesson2_2_1, lesson2_2_2));
        allChapters.add(chapter2_2);

        // --- Chapter 2.3: Talking about the Weather ---
        LessonInfoResponse lesson2_3_1 = new LessonInfoResponse(++exerciseIdCounter, "Describing the Weather: 'It's sunny/rainy.'", List.of(exerciseIdCounter));
        LessonInfoResponse lesson2_3_2 = new LessonInfoResponse(++exerciseIdCounter, "Asking for the Forecast", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter2_3 = new ChapterInfoResponse(23, "Talking about the Weather", "Use the weather as an easy conversation starter.", List.of(lesson2_3_1, lesson2_3_2));
        allChapters.add(chapter2_3);

        // --- Chapter 2.4: Booking Accommodation ---
        LessonInfoResponse lesson2_4_1 = new LessonInfoResponse(++exerciseIdCounter, "Booking a Hotel Room", List.of(exerciseIdCounter));
        LessonInfoResponse lesson2_4_2 = new LessonInfoResponse(++exerciseIdCounter, "Checking In and Checking Out", List.of(exerciseIdCounter));
        ChapterInfoResponse chapter2_4 = new ChapterInfoResponse(24, "Booking Accommodation", "Handle your lodging needs while traveling.", List.of(lesson2_4_1, lesson2_4_2));
        allChapters.add(chapter2_4);

        // =======================================================================
        // Create a single, unified Section
        // =======================================================================
        SectionInfoResponse unifiedSection = new SectionInfoResponse(
                1, // The ID for this single section
                "Vilingo English Course", // A new, all-encompassing title
                allChapters // The list containing all 8 chapters
        );

        // Return a list containing just this one section
        return List.of(unifiedSection);
    }

    private List<RecommendVideoResponse> initializeRecommendedVideos() {
        List<RecommendVideoResponse> videos = new ArrayList<>();

        // 创建12个推荐视频，标题和ID都是直接指定的
        videos.add(createRecommendation(2001, "Greetings & Introductions", 300, 101));
        videos.add(createRecommendation(2002, "Introducing Yourself", 320, 102));
        videos.add(createRecommendation(2003, "Saying Thank You", 310, 103));
        videos.add(createRecommendation(2004, "How to Apologize", 330, 104));
        videos.add(createRecommendation(2005, "Asking for Directions", 300, 105));
        videos.add(createRecommendation(2006, "Telling Time", 340, 106));
        videos.add(createRecommendation(2007, "Shopping for Clothes", 320, 107));
        videos.add(createRecommendation(2008, "Simple Bargaining", 310, 108));
        videos.add(createRecommendation(2009, "Ordering at a Restaurant", 350, 109));
        videos.add(createRecommendation(2010, "Taking a Taxi", 300, 110));
        videos.add(createRecommendation(2011, "Talking About Weather", 330, 111));
        videos.add(createRecommendation(2012, "Booking a Hotel", 320, 112));

        return videos;
    }

    /**
     * 一个辅助方法，用于快速创建 RecommendVideoResponse 对象，让代码更整洁。
     */
    private RecommendVideoResponse createRecommendation(int id, String title, int height, int eid) {
        var rec = new RecommendVideoResponse();
        rec.setId(id);
        rec.setTitle(title);
        rec.setHeight(height);
        rec.setEid(eid);
        // 封面 Key 的命名规则与练习项ID关联 (例如 eid=101 -> coverKey="cover/1011.jpg")
        int exerciseItemId = eid * 10 + 1;
        rec.setCoverKey("cover/" + exerciseItemId + "cover" + ".jpg");
        return rec;
    }
}