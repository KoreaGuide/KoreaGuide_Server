package com.example.koreaguide.service;

import com.example.koreaguide.model.entity.MyTestResult;
import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.MyWordFolder;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.entity.Word;
import com.example.koreaguide.model.enumclass.MyWordStatus;
import com.example.koreaguide.model.enumclass.QuizType;
import com.example.koreaguide.model.enumclass.TestResultStatus;
import com.example.koreaguide.model.enumclass.UserStatus;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.MyWordFolderApiRequest;
import com.example.koreaguide.model.network.request.QuizListApiRequest;
import com.example.koreaguide.model.network.request.QuizResultApiRequest;
import com.example.koreaguide.model.network.request.QuizResultDetailApiRequest;
import com.example.koreaguide.model.network.response.MyWordListApiResponse;
import com.example.koreaguide.model.network.response.QuizListApiResponse;
import com.example.koreaguide.model.network.response.QuizMultipleChoiceApiResponse;
import com.example.koreaguide.model.network.response.WordApiResponse;
import com.example.koreaguide.repository.MyTestResultRepository;
import com.example.koreaguide.repository.MyWordFolderRepository;
import com.example.koreaguide.repository.MyWordRepository;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/*
 * @author : Jisoo Kim
 * @date: 2021/04/28 9:22 오후
*/
@Service
public class QuizApiLogicService {
    @Autowired
    WordRepository wordRepository;

    @Autowired
    MyWordFolderRepository myWordFolderRepository;

    @Autowired
    MyWordRepository myWordRepository;

    @Autowired
    MyTestResultRepository myTestResultRepository;

    @Autowired
    UserRepository userRepository;

    List<String> alphabets= Arrays.asList(new String[]{"가", "나","다","라","마","바","사","아","자","차","카","타","파","하",
                                            "기","니","디","리","미","비","시","이","지","치","키","티","피","히",
                                            "거","너","더","러","머","버","서","어","저","처","커","터","퍼","허",
                                            "고","노","도","로","모","보","소","오","조","초","코","토","포","호",
                                            "그","느","드","르","므","브","스","으","즈","츠","크","트","프","흐",
                                            "구","누","두","루","무","부","수","우","주","추","쿠","투","푸","후",
                                            "규","뉴","듀","류","뮤","뷰","슈","유","쥬","츄","큐","튜","퓨","휴",
                                            "겨","녀","뎌","려","며","벼","셔","여","져","쳐","켜","텨","펴","혀",
                                            "교","뇨","됴","료","묘","뵤","쇼","요","죠","쵸","쿄","툐","표","효",
                                            "개","내","대","래","매","배","새","애","재","채","캐","태","패","해",
                                            "게","네","데","레","메","베","세","에","제","체","케","테","페","헤",
                                            "걔","계","례","셰","예","얘","쟤","폐",
                                            "갸","냐","댜","랴","먀","뱌","샤","야","쟈","챠","캬","탸","퍄","햐",
                                            "괘","괴","뇌","뢰","뵈","봬","쇠","죄","최","왜","외",
                                            "강","낭","당","랑","망","방","상","앙","장","창","캉","탕","팡","항",
                                            "각","낙","닥","락","막","박","삭","악","작","착","칵","탁","팍","학",
                                            "간","난","단","란","만","반","산","안","잔","찬","칸","탄","판","한",
                                            "갓","낫","닷","랏","맛","밧","삿","앗","잣","찻","캇","탓","팟","핫",
                                            "같","낱","맡","밭","샅","앝","팥",
                                            "감","남","담","람","맘","밤","삼","암","잠","참","캄","탐","팜","함",
                                            "갑","납","답","랍","맙","밥","삽","압","잡","찹","캅","탑","팝","합",
                                            "갈","날","달","랄","말","발","살","알","잘","찰","칼","탈","팔","할",
                                            "겅","넝","덩","렁","멍","벙","성","엉","정","청","컹","텅","펑","헝",
                                            "걱","넉","덕","럭","먹","벅","석","억","적","척","컥","턱","퍽","헉",
                                            "건","넌","던","런","먼","번","선","언","전","천","컨","턴","펀","헌",
                                            "것","넛","덧","럿","멋","벗","섯","엇","젓","첫","컷","텃","펏","헛",
                                            "겉","넡","벚","젖","벛",
                                            "검","넘","덤","럼","멈","범","섬","엄","점","첨","컴","텀","펌","험",
                                            "겁","넙","덥","럽","멉","법","섭","업","접","첩","컵","텁","펍","헙",
                                            "걸","널","덜","럴","멀","벌","설","얼","절","철","컬","털","펄","헐",
                                            "공","농","동","롱","몽","봉","송","옹","종","총","콩","통","퐁","홍",
                                            "곡","녹","독","록","목","복","속","옥","족","촉","콕","톡","폭","혹",
                                            "곤","논","돈","론","몬","본","손","온","존","촌","콘","톤","폰","혼",
                                            "곳","놋","돗","롯","못","봇","솟","옷","좃","촛","콧","톳","폿","홋",
                                            "곰","놈","동","롬","몸","봄","솜","옴","좀","촘","콤","톰","폼","홈",
                                            "곱","놉","돕","롭","몹","봅","솝","옵","좁","촙","콥","톱","폽","홉",
                                            "골","놀","돌","롤","몰","볼","솔","올","졸","촐","콜","톨","폴","홀",
                                            "긱","닉","딕","릭","믹","빅","식","익","직","칙","킥","틱","픽","힉",
                                            "긴","닌","딘","린","민","빈","신","인","진","친","킨","틴","핀","힌",
                                            "깃","닛","딧","릿","밋","빗","싯","잇","짓","칫","킷","팃","핏","힛",
                                            "깁","닙","딥","립","밉","빕","십","입","집","칩","킵","팁","핍","힙",
                                            "깅","닝","딩","링","밍","빙","싱","잉","징","칭","킹","팅","핑","힝",
                                            "길","닐","딜","릴","밀","빌","실","일","질","칠","킬","틸","필","힐",
                                            "김","님","딤","림","밈","빔","심","임","짐","침","킴","팀","핌","힘",
                                            "극","늑","득","륵","믁","븍","슥","윽","즉","측","큭","특","픅","흑",
                                            "근","는","든","른","믄","븐","슨","은","즌","츤","큰","튼","픈","흔",
                                            "긍","능","등","릉","믕","븡","승","응","증","층","킁","틍","픙","흥",
                                            "글","늘","들","를","믈","블","슬","을","즐","츨","클","틀","플","흘",
                                            "급","늡","듭","릅","믑","늪","습","읍","즙","츱","큽","틉","픕","흡",
                                            "금","늠","듬","름","믐","븜","슴","음","즘","츰","큼","틈","픔","흠",
                                            "국","눅","둑","룩","묵","북","숙","욱","죽","축","쿡","툭","푹","훅",
                                            "군","눈","둔","룬","문","분","순","운","준","춘","쿤","툰","푼","훈",
                                            "궁","숭","웅","충","쿵","풍","훙",
                                            "균","륜","슌","윤","튠","력","역",
                                            "굴","눌","둘","룰","물","불","술","울","줄","출","쿨","툴","풀","훌",
                                            "격","녁","뎍","력","멱","벽","셕","역","젹","쳑","켴","텩","펵","혁",
                                            "견","년","뎐","련","면","변","션","연","젼","쳔","켠",
                                            "겸","념","뎜","렴","몀","볌","셤","염","졈","쳠","켬","폄","혐","위",
                                            "찮","읆","옮","읊","않","되","돼","괘","괴","롭","괜","희","의","삶",
                                            "갤","낼","맬","랠","댈","밸","샐","앨","잴","챌","캘","탤","팰","핼",
                                            "경","녕","뎡","령","명","병","셩","영","졍","쳥","켱",
                                            "근","는","든","른","믄","븐","슨","음","즘","츤","큰","튼","픈","흔",
                                            "글","늘","들","를","믈","블","슬","을","즐","츨","클","틀","플","흘"});

    public QuizListApiResponse getQuizWordList(Integer id, Header<QuizListApiRequest> request) {
        QuizListApiRequest body = request.getData();
        Optional<MyWordFolder> wordFolder = myWordFolderRepository.findById(body.getFolderId());
        return wordFolder.map(selectedWordFolder->{
            List<MyWord> myWordList= myWordRepository.findAllByMyWordFolderId(selectedWordFolder.getId());
            List<Word> words = new ArrayList<Word>();
            for(int i=0;i<myWordList.size();i++){
                words.add(wordRepository.getOne(myWordList.get(i).getWord().getId()));
            }
            QuizListApiResponse response= null;
            if(!myWordList.isEmpty()){
                if(body.getQuizType()==QuizType.MATCH || body.getQuizType() == QuizType.LISTEN) {
                   response= responseMatch(words, body.getQuizType(), body.getFolderId());
                   System.out.println("DONE");
                }else if(body.getQuizType() == QuizType.SPELLING_E){
                    response = responseSpellingEasy(words,body.getQuizType(),body.getFolderId());
                }else if(body.getQuizType()==QuizType.SPELLING_H){
                    response = responseSpellingHard(words,body.getQuizType(),body.getFolderId());
                }else{
                    throw new KoreaGuideException(KoreaGuideError.WRONG_FORMAT);
                }
            }else{
                throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_MYWORDFOLDER);
            }
            return response;
        }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORDFOLDER));
    }

    private QuizListApiResponse responseMatch(List<Word> myWordList, QuizType type,Integer folderId) {
        List<QuizMultipleChoiceApiResponse> quizMultipleChoiceApiResponsesList = new ArrayList<QuizMultipleChoiceApiResponse>();
        for (int i = 0; i < myWordList.size(); i++) {
            List<WordApiResponse> multipleChoiceList = new ArrayList<WordApiResponse>();
            int[] wordIndexList = getRandomIndex(myWordList.get(i).getId());
            for (int j = 0; j < wordIndexList.length; j++) {
                Word word = wordRepository.getOne(wordIndexList[j]);
                System.out.println("WORD j: "+j+" is   "+word.getWordEng());
                WordApiResponse wordApiResponse = WordApiResponse.builder()
                        .id(word.getId())
                        .wordKor(word.getWordKor())
                        .wordEng(word.getWordEng())
                        .meaningEng1(word.getMeaningEng1())
                        .meaningKor1(word.getMeaningKor1())
                        .build();
                multipleChoiceList.add(wordApiResponse);
                System.out.println("ADDED!!!!");

            }
            System.out.println("size: "+multipleChoiceList.size());
            System.out.println("size: "+multipleChoiceList.size());

            WordApiResponse thisWordApiResponse = WordApiResponse.builder()
                    .id(myWordList.get(i).getId())
                    .wordKor(myWordList.get(i).getWordKor())
                    .wordEng(myWordList.get(i).getWordEng())
                    .meaningEng1(myWordList.get(i).getMeaningEng1())
                    .meaningKor1(myWordList.get(i).getMeaningKor1())
                    .build();
            multipleChoiceList.add(thisWordApiResponse);
            Collections.shuffle(multipleChoiceList);
            System.out.println("size: "+multipleChoiceList.size());

            WordApiResponse selectedWordApiResponse = WordApiResponse.builder()
                    .wordKor(myWordList.get(i).getWordKor())
                    .id(myWordList.get(i).getId())
                    .wordEng(myWordList.get(i).getWordEng())
                    .image(myWordList.get(i).getImage()).build();

            QuizMultipleChoiceApiResponse quizMultipleChoiceApiResponse = QuizMultipleChoiceApiResponse.builder()
                    .selectedWord(selectedWordApiResponse)
                    .wordChoiceList(multipleChoiceList).build();
            quizMultipleChoiceApiResponsesList.add(quizMultipleChoiceApiResponse);
        }
        Collections.shuffle(quizMultipleChoiceApiResponsesList);
        QuizListApiResponse quizListApiResponse = QuizListApiResponse.builder()
                .quizType(type)
                .folderId(folderId)
                .quizList(quizMultipleChoiceApiResponsesList).build();
        return quizListApiResponse;
    }
    private QuizListApiResponse responseSpellingEasy(List<Word> myWordList, QuizType type,Integer folderId) {
        List<QuizMultipleChoiceApiResponse> quizMultipleChoiceApiResponsesList = new ArrayList<QuizMultipleChoiceApiResponse>();
        for (int i = 0; i < myWordList.size(); i++) {
            List<String> multipleChoiceList = new ArrayList<String>();

            String selectedWord = myWordList.get(i).getWordKor();
            System.out.println("___ SELECTED WORD: "+selectedWord);

            for(int j=0;j<selectedWord.length();j++){
                multipleChoiceList.add(selectedWord.substring(j,j+1));
                System.out.println("___ ADDED IS: "+selectedWord.substring(j,j+1));
            }
            int[] wordIndexList = getRandomIndexForSpelling(alphabets.size(),12-selectedWord.length());
            for (int j = 0; j < wordIndexList.length; j++) {
                multipleChoiceList.add(alphabets.get(wordIndexList[j]));
                System.out.println("___ CHOICE ADDED IS: "+alphabets.get(wordIndexList[j]));

            }

            Collections.shuffle(multipleChoiceList);
            System.out.println("size: "+multipleChoiceList.size());

            WordApiResponse selectedWordApiResponse = WordApiResponse.builder()
                    .wordKor(myWordList.get(i).getWordKor())
                    .id(myWordList.get(i).getId())
                    .wordEng(myWordList.get(i).getWordEng())
                    .image(myWordList.get(i).getImage()).build();

            QuizMultipleChoiceApiResponse quizMultipleChoiceApiResponse = QuizMultipleChoiceApiResponse.builder()
                    .selectedWord(selectedWordApiResponse)
                    .alphabetChoiceList(multipleChoiceList).build();
            quizMultipleChoiceApiResponsesList.add(quizMultipleChoiceApiResponse);
        }
        //퀴즈 자체를 셔플하기
        Collections.shuffle(quizMultipleChoiceApiResponsesList);
        QuizListApiResponse quizListApiResponse = QuizListApiResponse.builder()
                .quizType(type)
                .folderId(folderId)
                .quizList(quizMultipleChoiceApiResponsesList).build();
        return quizListApiResponse;
    }
    private QuizListApiResponse responseSpellingHard(List<Word> myWordList, QuizType type,Integer folderId) {
        List<QuizMultipleChoiceApiResponse> quizMultipleChoiceApiResponsesList = new ArrayList<QuizMultipleChoiceApiResponse>();
        for (int i = 0; i < myWordList.size(); i++) {
            List<String> multipleChoiceList = new ArrayList<String>();
            WordApiResponse selectedWordApiResponse = WordApiResponse.builder()
                    .wordKor(myWordList.get(i).getWordKor())
                    .id(myWordList.get(i).getId())
                    .wordEng(myWordList.get(i).getWordEng())
                    .image(myWordList.get(i).getImage()).build();

            QuizMultipleChoiceApiResponse quizMultipleChoiceApiResponse = QuizMultipleChoiceApiResponse.builder()
                    .selectedWord(selectedWordApiResponse).build();
            quizMultipleChoiceApiResponsesList.add(quizMultipleChoiceApiResponse);
        }
        //퀴즈 자체를 셔플하기
        Collections.shuffle(quizMultipleChoiceApiResponsesList);
        QuizListApiResponse quizListApiResponse = QuizListApiResponse.builder()
                .quizType(type)
                .folderId(folderId)
                .quizList(quizMultipleChoiceApiResponsesList).build();
        return quizListApiResponse;
    }

    private int[] getRandomIndex(Integer wordId){
        int indices[] = new int[3];
        int min = 1;
        int max = 1000;
        for(int i=0;i<3;i++){
            int selectedNumber=(int) ((Math.random() * (max - min)) + min);
            System.out.println("______SELECTED!!! : "+selectedNumber);
            while(wordRepository.findById(selectedNumber)==null || selectedNumber==wordId){
                System.out.println("______SELECTED is NULL!!! : "+selectedNumber);
                selectedNumber=(int) ((Math.random() * (max - min)) + min);
            }
            indices[i]=selectedNumber;
            for(int j=0;j<i;j++){
                if(indices[i]==indices[j]){
                    i--;
                }
            }
        }
        return indices;
    }
    private int[] getRandomIndexForSpelling(Integer size, Integer count){
        System.out.println("get index for "+count);
        int indices[] = new int[count];
        int min = 0;
        int max = size;
        for(int i=0;i<count;i++){
            int selectedNumber=(int) ((Math.random() * (max - min)) + min);
            System.out.println("______SELECTED!!! : "+selectedNumber);
            System.out.println("___ SELECTED ALPHA IS: "+alphabets.get(selectedNumber));
            indices[i]=selectedNumber;
            for(int j=0;j<i;j++){
                if(indices[i]==indices[j]){
                    i--;
                }
            }
        }
        return indices;
    }
    void updateTestResult(int userId, LocalDate today,TestResultStatus resultStatus){
        Optional<MyTestResult> testResult = myTestResultRepository.findByDateAndUserId(today,userId);
        if(!testResult.isPresent()){
            System.out.println("\nTEST RESULT is NULL");
            Integer correctNum = 0;
            Integer wrongNum = 0;
            User selectedUser = userRepository.getOne(userId);
            if(resultStatus == TestResultStatus.WRONG){
                wrongNum +=1;
            }else{
                correctNum +=1;
            }
            MyTestResult myTestResult = MyTestResult.builder()
                    .date(today)
                    .correctNumber(correctNum)
                    .wrongNumber(wrongNum)
                    .totalNumber(correctNum+wrongNum)
                    .user(selectedUser)
                    .build();
            System.out.println("\nMY TEST RESULT : "+myTestResult);

            myTestResultRepository.save(myTestResult);
        }
        testResult.map(selectedTestResult-> {
            System.out.println("\nTEST RESULT is FOUND");

            if (resultStatus == TestResultStatus.WRONG) {
                        selectedTestResult.setWrongNumber(selectedTestResult.getWrongNumber() + 1);
                    } else {
                        selectedTestResult.setCorrectNumber(selectedTestResult.getCorrectNumber() + 1);
                    }
                    selectedTestResult.setTotalNumber(selectedTestResult.getTotalNumber() + 1);
                    myTestResultRepository.save(selectedTestResult);
            System.out.println("\nMY TEST RESULT : "+selectedTestResult);

            return selectedTestResult;
        });
    }

    public Header postTestResult(Integer id, Header<QuizResultApiRequest> request) {
        QuizResultApiRequest body = request.getData();
        List<QuizResultDetailApiRequest> resultList = body.getQuizResults();
        for(int i=0;i<resultList.size();i++){
            Optional<MyWordFolder> wordFolder= myWordFolderRepository.findById(resultList.get(i).getOriginalFolderId());
            int finalI = i;
            wordFolder.map(selectedWordFolder->{
                Word testWord = wordRepository.getOne(resultList.get(finalI).getWordId());
//                System.out.println("test word: "+testWord.getWordEng());
                Optional<MyWord> word = myWordRepository.findByMyWordFolderAndWord(selectedWordFolder,testWord);

                word.map(selectedWord ->{
//                    System.out.println("selectedWord: "+selectedWord.getWord());
                    Optional<MyWordFolder> destWordFolder = myWordFolderRepository.findById(resultList.get(finalI).getFinalFolderId());

                    destWordFolder.map(selectedDestWordFolder->{
//                        System.out.println("destWordFolder: "+selectedDestWordFolder.getId());
                        TestResultStatus result = resultList.get(finalI).getResultStatus();
                        selectedWord.setMyWordFolder(selectedDestWordFolder);

                        if(result == TestResultStatus.CORRECT){
                            selectedWord.setWordStatus(MyWordStatus.KNOW);
                        }else{
                            selectedWord.setWordStatus(MyWordStatus.DONT_KNOW);
                        }

                        updateTestResult(id, LocalDate.now(),result);
                        myWordRepository.save(selectedWord);

                        Integer wordCountDest = myWordRepository.findAllByMyWordFolderId(selectedDestWordFolder.getId()).size();
                        Integer wordCountOrigin = myWordRepository.findAllByMyWordFolderId(selectedWordFolder.getId()).size();
                        selectedWordFolder.setWordCount(wordCountDest);
                        selectedDestWordFolder.setWordCount(wordCountOrigin);

                        myWordFolderRepository.save(selectedWordFolder);
                        myWordFolderRepository.save(selectedDestWordFolder);

                        return selectedDestWordFolder;
                    }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORDFOLDER));
                    return selectedWord;
                }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORD));
                return selectedWordFolder;
            }).orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_MYWORDFOLDER));
        }
        return Header.OK();
    }
}
