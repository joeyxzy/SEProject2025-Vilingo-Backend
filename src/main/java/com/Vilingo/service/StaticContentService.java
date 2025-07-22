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
        Map<Integer, ExerciseInfoResponse> exerciseMap = new HashMap<>();

        // --- 练习 101: Greetings ---
        VideoExerciseResponse video101 = new VideoExerciseResponse();
        video101.setId(1011);
        video101.setVideoKey("video/1011video.mp4");
        video101.setSrtKey("srt/1011srt.srt");
        RetellingExerciseResponse retelling101 = new RetellingExerciseResponse(1012, "Sir Ken Robinson makes an entertaining and profoundly moving case for creating an education system that nurtures (rather than undermines) creativity.");
        ExerciseInfoResponse exercise101 = new ExerciseInfoResponse();
        exercise101.setXp(10);
        exercise101.setDifficulty("easy");
        exercise101.setItems(List.of(video101, retelling101));
        exerciseMap.put(101, exercise101);

        // --- 练习 102: Introductions ---
        VideoExerciseResponse video102 = new VideoExerciseResponse();
        video102.setId(1021);
        video102.setVideoKey("video/1021video.mp4");
        video102.setSrtKey("srt/1021srt.srt");
        RetellingExerciseResponse retelling102 = new RetellingExerciseResponse(1022, "In a talk from the cutting edge of technology, OpenAI cofounder Greg Brockman explores the underlying design principles of ChatGPT and demos some mind-blowing, unreleased plug-ins for the chatbot that sent shockwaves across the world. After the talk, head of TED Chris Anderson joins Brockman to dig into the timeline of ChatGPT's development and get Brockman's take on the risks, raised by many in the tech industry and beyond, of releasing such a powerful tool into the world.");
        ExerciseInfoResponse exercise102 = new ExerciseInfoResponse();
        exercise102.setXp(15);
        exercise102.setDifficulty("easy");
        exercise102.setItems(List.of(video102, retelling102));
        exerciseMap.put(102, exercise102);

        // --- 练习 103: Gratitude ---
        VideoExerciseResponse video103 = new VideoExerciseResponse();
        video103.setId(1031);
        video103.setVideoKey("video/1031video.mp4");
        video103.setSrtKey("srt/1031srt.srt");
        RetellingExerciseResponse retelling103 = new RetellingExerciseResponse(1032, "On any given day we're lied to from 10 to 200 times, and the clues to detect those lies can be subtle and counter-intuitive. Pamela Meyer, author of \"Liespotting,\" shows the manners and \"hotspots\" used by those trained to recognize deception -- and she argues honesty is a value worth preserving. (Contains mature content)");
        ExerciseInfoResponse exercise103 = new ExerciseInfoResponse();
        exercise103.setXp(15);
        exercise103.setDifficulty("easy");
        exercise103.setItems(List.of(video103, retelling103));
        exerciseMap.put(103, exercise103);

        // --- 练习 104: Apologies ---
        VideoExerciseResponse video104 = new VideoExerciseResponse();
        video104.setId(1041);
        video104.setVideoKey("video/1041video.mp4");
        video104.setSrtKey("srt/1041srt.srt");
        RetellingExerciseResponse retelling104 = new RetellingExerciseResponse(1042, "Elon Musk discusses his new project digging tunnels under LA, the latest from Tesla and SpaceX and his motivation for building a future on Mars in conversation with TED's Head Curator, Chris Anderson.");
        ExerciseInfoResponse exercise104 = new ExerciseInfoResponse();
        exercise104.setXp(18);
        exercise104.setDifficulty("easy");
        exercise104.setItems(List.of(video104, retelling104));
        exerciseMap.put(104, exercise104);

        // --- 练习 105: Asking Directions ---
        VideoExerciseResponse video105 = new VideoExerciseResponse();
        video105.setId(1051);
        video105.setVideoKey("video/1051video.mp4");
        video105.setSrtKey("srt/1051srt.srt");
        RetellingExerciseResponse retelling105 = new RetellingExerciseResponse(1052, "Simon Sinek explains inspiring leadership through a simple yet powerful model, the core of which is a \"Golden Circle,\" meaning the fundamental source of leader qualities is answering \"Why?\". He cites successful examples like Apple Inc., Martin Luther King Jr., and the Wright brothers, while using TiVo's digital video recorder as a typical case of failure. Although TiVo recently won a lawsuit, causing its stock price to triple, it is still struggling.");
        ExerciseInfoResponse exercise105 = new ExerciseInfoResponse();
        exercise105.setXp(20);
        exercise105.setDifficulty("medium");
        exercise105.setItems(List.of(video105, retelling105));
        exerciseMap.put(105, exercise105);

        // --- 练习 106: Telling Time ---
        VideoExerciseResponse video106 = new VideoExerciseResponse();
        video106.setId(1061);
        video106.setVideoKey("video/1061video.mp4");
        video106.setSrtKey("srt/1061srt.srt");
        RetellingExerciseResponse retelling106 = new RetellingExerciseResponse(1062, "Infidelity is the ultimate betrayal. But does it have to be? Relationship therapist Esther Perel examines why people cheat, and unpacks why affairs are so traumatic: because they threaten our emotional security. In infidelity, she sees something unexpected — an expression of longing and loss. A must-watch for anyone who has ever cheated or been cheated on, or who simply wants a new framework for understanding relationships.");
        ExerciseInfoResponse exercise106 = new ExerciseInfoResponse();
        exercise106.setXp(20);
        exercise106.setDifficulty("medium");
        exercise106.setItems(List.of(video106, retelling106));
        exerciseMap.put(106, exercise106);

        // --- 练习 107: Shopping ---
        VideoExerciseResponse video107 = new VideoExerciseResponse();
        video107.setId(1071);
        video107.setVideoKey("video/1071video.mp4");
        video107.setSrtKey("srt/1071srt.srt");
        RetellingExerciseResponse retelling107 = new RetellingExerciseResponse(1072, "Have you ever felt like you're talking, but nobody is listening? Here's Julian Treasure to help. In this useful talk, the sound expert demonstrates the how-to's of powerful speaking -- from some handy vocal exercises to tips on how to speak with empathy. A talk that might help the world sound more beautiful.");
        ExerciseInfoResponse exercise107 = new ExerciseInfoResponse();
        exercise107.setXp(22);
        exercise107.setDifficulty("medium");
        exercise107.setItems(List.of(video107, retelling107));
        exerciseMap.put(107, exercise107);

        // --- 练习 108: Bargaining ---
        VideoExerciseResponse video108 = new VideoExerciseResponse();
        video108.setId(1081);
        video108.setVideoKey("video/1081video.mp4");
        video108.setSrtKey("srt/1081srt.srt");
        RetellingExerciseResponse retelling108 = new RetellingExerciseResponse(1082, "What keeps us happy and healthy as we go through life? If you think it's fame and money, you're not alone – but, according to psychiatrist Robert Waldinger, you're mistaken. As the director of a 75-year-old study on adult development, Waldinger has unprecedented access to data on true happiness and satisfaction. In this talk, he shares three important lessons learned from the study as well as some practical, old-as-the-hills wisdom on how to build a fulfilling, long life.");
        ExerciseInfoResponse exercise108 = new ExerciseInfoResponse();
        exercise108.setXp(25);
        exercise108.setDifficulty("hard");
        exercise108.setItems(List.of(video108, retelling108));
        exerciseMap.put(108, exercise108);

        // --- 练习 109: Ordering Food ---
        VideoExerciseResponse video109 = new VideoExerciseResponse();
        video109.setId(1091);
        video109.setVideoKey("video/1091video.mp4");
        video109.setSrtKey("srt/1091srt.srt");
        RetellingExerciseResponse retelling109 = new RetellingExerciseResponse(1092, "In 2014, the world avoided a global outbreak of Ebola, thanks to thousands of selfless health workers -- plus, frankly, some very good luck. In hindsight, we know what we should have done better. So, now's the time, Bill Gates suggests, to put all our good ideas into practice, from scenario planning to vaccine research to health worker training. As he says, \"There's no need to panic ... but we need to get going.\"");
        ExerciseInfoResponse exercise109 = new ExerciseInfoResponse();
        exercise109.setXp(20);
        exercise109.setDifficulty("medium");
        exercise109.setItems(List.of(video109, retelling109));
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

    // 在 StaticContentService.java 中

    /**
     * 推荐视频初始化方法，已扩充至16个视频。
     */
// 在 StaticContentService.java 中

    /**
     * 推荐视频初始化方法，已扩充至16个视频，并重新应用了难度分级。
     */
    private List<RecommendVideoResponse> initializeRecommendedVideos() {
        List<RecommendVideoResponse> videos = new ArrayList<>();

        // --- Easy Difficulty (4 videos) ---
        videos.add(createRecommendation(2001, "Do schools kill creativity?", 300, 101, "easy"));
        videos.add(createRecommendation(2002, "The inside story of ChatGPT's astonishing potential", 320, 102, "easy"));
        videos.add(createRecommendation(2003, "How to spot a liar", 310, 103, "easy"));
        videos.add(createRecommendation(2004, "The future we're building -- and boring", 330, 104, "easy"));

        // --- Medium Difficulty (4 videos) ---
        videos.add(createRecommendation(2005, "How great leaders inspire action", 300, 105, "medium"));
        videos.add(createRecommendation(2006, "Rethinking infidelity ... a talk for anyone who has ever loved", 340, 106, "medium"));
        videos.add(createRecommendation(2007, "How to speak so that people want to listen", 320, 107, "medium"));
        videos.add(createRecommendation(2008, "What makes a good life? Lessons from the longest study on happiness", 310, 108, "medium"));

        // --- Hard Difficulty (4 videos) ---
        videos.add(createRecommendation(2009, "The next outbreak? We're not ready", 350, 109, "hard"));
        videos.add(createRecommendation(2010, "Your body language may shape who you are", 300, 110, "hard"));
        videos.add(createRecommendation(2011, "How we can help the \"forgotten middle\" reach their full potential", 330, 111, "hard"));
        videos.add(createRecommendation(2012, "How generational stereotypes hold us back at work", 320, 112, "hard"));

        // --- Expert Difficulty (新增的4个，定义为 'hard' 或一个新的难度等级) ---
        // 为了保持每4个一个难度，我们继续使用 'hard'，或者你可以定义一个新的，比如 'expert'。
        // 这里我们继续用 'hard' 来确保推荐逻辑能找到它们。
        videos.add(createRecommendation(2013, "Are you following your dreams?", 310, 113, "hard"));
        videos.add(createRecommendation(2014, "How did Hitler rise to power?", 340, 114, "hard"));
        videos.add(createRecommendation(2015, "How I Made OVER $2,000 From ONE Video", 300, 115, "hard"));
        videos.add(createRecommendation(2016, "WW84 | Opening Scene", 320, 116, "hard"));

        return videos;
    }

    /**
     * 更新辅助方法，增加 difficulty 参数。
     */
    private RecommendVideoResponse createRecommendation(int id, String title, int height, int eid, String difficulty) {
        var rec = new RecommendVideoResponse();
        rec.setId(id);
        rec.setTitle(title);
        rec.setHeight(height);
        rec.setEid(eid);
        rec.setDifficulty(difficulty); // 设置难度

        int exerciseItemId = eid * 10 + 1;
        // 确保封面Key的命名是正确的
        rec.setCoverKey("cover/" + exerciseItemId + "cover" + ".jpg");

        return rec;
    }
}