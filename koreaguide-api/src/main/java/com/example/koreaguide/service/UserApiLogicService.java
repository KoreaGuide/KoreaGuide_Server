package com.example.koreaguide.service;

import com.example.koreaguide.ifs.CrudInterface;
import com.example.koreaguide.model.entity.MapFile;
import com.example.koreaguide.model.entity.MyWord;
import com.example.koreaguide.model.entity.MyWordFolder;
import com.example.koreaguide.model.entity.User;
import com.example.koreaguide.model.enumclass.UserStatus;
import com.example.koreaguide.model.exception.EmailNotExistedException;
import com.example.koreaguide.model.exception.KoreaGuideError;
import com.example.koreaguide.model.exception.KoreaGuideException;
import com.example.koreaguide.model.exception.PasswordWrongException;
import com.example.koreaguide.model.network.Header;
import com.example.koreaguide.model.network.request.SessionRequestDto;
import com.example.koreaguide.model.network.request.UserApiRequest;
import com.example.koreaguide.model.network.response.SessionResponseDto;
import com.example.koreaguide.model.network.response.UserApiResponse;
import com.example.koreaguide.repository.MapFileRepository;
import com.example.koreaguide.repository.MyWordFolderRepository;
import com.example.koreaguide.repository.MyWordRepository;
import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.repository.WordRepository;
import com.example.koreaguide.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.swing.text.html.Option;
import javax.validation.constraints.Email;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserApiLogicService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private MyWordRepository myWordRepository;

    @Autowired
    private MyWordFolderRepository myWordFolderRepository;

    @Autowired
    MapFileRepository mapFileRepository;

    public UserApiResponse create(Header<UserApiRequest> request) {
        System.out.println("IN CREATE!! "+request.getData().getEmail()+"  nickname:"+request.getData().getNickname());
        // 회원가입!

        // 1.request data 가져오고
        UserApiRequest body = request.getData();

        // 비번 암호화
        String encodedPassword = passwordEncoder.encode(body.getPassword());

        // 이메일 중복 체크 한번더 확인
        Optional<User> userWithEmail = userRepository.findByEmail(body.getEmail());
        if(userWithEmail.isPresent()){
            throw new KoreaGuideException(KoreaGuideError.DUPLICATE_ERROR_USER,"Email already exists");
        }else {
            // 2. user 생성
            System.out.println("INSIDE!! "+request.getData().getEmail()+"  nickname:"+request.getData().getNickname());
            User user = User.builder()
                    .email(body.getEmail())
                    .password(encodedPassword)
                    .nickname(body.getNickname())
                    .createdAt(LocalDateTime.now())
                    .createdBy("Admin")
                    .lastLoginAt(null)
                    .status(UserStatus.INACTIVE)
                    .weekAttendance(0)
                    .build();
//            String token = jwtUtil.createAccessToken(body.getId(),body.getNickname());
//            System.out.println("ACCESS TOKEN"+token);
            User newUser = userRepository.save(user);
            System.out.println("CREATED is :"+newUser);
            if(newUser!=null){
                for(int i = 0 ;i<3;i++) {
                    Integer folderName = i+1;
                    MyWordFolder newMyWordFolder = MyWordFolder.builder()
                            .folderName(folderName.toString())
                            .wordCount(0)
                            .user(user).build();
                    MyWordFolder newCreatedFolder = myWordFolderRepository.save(newMyWordFolder);
                }
            }

            final String uploadPath = Paths.get("koreaguide-api","src","main","java","com","example","koreaguide","mapFiles").toString();
            File dir = new File(uploadPath);
            if (dir.exists() == false) {
                dir.mkdirs();
            }
            final String saveName = "mapFile_"+newUser.getId() + "." + "geojson";

            String defaultPath = Paths.get("koreaguide-api/src/main/java/com/example/koreaguide/mapFiles/mapFile_default.geojson").toString();
            String newPath = Paths.get("koreaguide-api/src/main/java/com/example/koreaguide/mapFiles/"+saveName).toString();

            File original = new File(defaultPath);
            File newFile = new File(newPath);

            try {

                FileInputStream fis = new FileInputStream(original); //읽을파일
                FileOutputStream fos = new FileOutputStream(newFile); //복사할파일

                int fileByte = 0;
                // fis.read()가 -1 이면 파일을 다 읽은것
                while((fileByte = fis.read()) != -1) {
                    fos.write(fileByte);
                }
                //자원사용종료
                fis.close();
                fos.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            MapFile ori = mapFileRepository.getByUser(userRepository.getOne(29));

            MapFile mapFile = MapFile.builder()
                    .fileName(saveName)
                    .createdAt(LocalDateTime.now())
                    .user(newUser)
                    .originalName(saveName)
                    .size(153871)
                    .build();

            mapFileRepository.save(mapFile);


//            MapFile defaultFile = mapFileRepository.getOne()


            // 3. 생성된 데이터 --> userapiresponse 리턴
//            return Header.OK(response(newUser),HttpStatus.CREATED);
            System.out.println("CREATED");
            return response(newUser);
        }
    }

    public UserApiResponse read(Integer id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(selectedUser->response(selectedUser))
                .orElseThrow(()->
                        new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER,"user")
                );

    }

    public Header<UserApiResponse> update(Integer id,Header<UserApiRequest> request) {
        UserApiRequest body = request.getData();
        Optional<User> user = userRepository.findById(id);
        if(user == null){
            Header.NOTFOUNDERROR("Cannot find user");
        }
        String encodedPassword = passwordEncoder.encode(body.getPassword());
        return user.map(selectedUser->{
            if(!body.getEmail().isEmpty()){
                selectedUser.setEmail(body.getEmail());
            }
            if(!body.getNickname().isEmpty()){
                selectedUser.setNickname(body.getNickname());
            }
            if(!body.getPassword().isEmpty()){
                selectedUser.setPassword(encodedPassword);
            }
            selectedUser.setCreatedAt(user.get().getCreatedAt())
                        .setCreatedBy(user.get().getCreatedBy());
            return selectedUser;
            })
                .map(updatedUser -> userRepository.save(updatedUser))
                .map(finalUser->Header.OK(response(finalUser),HttpStatus.OK))
                .orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER,"user"));
    }

    public Header delete(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user
                .map(userSelected->{
                            System.out.println("USER SELECTED: "+userSelected);
                            //유저 삭제할때 해당 사용자의 단어장에 있는 단어도 다 삭제
                            List<MyWordFolder> myWordFolderList =myWordFolderRepository.findAllByUserId(id);
                            if(!myWordFolderList.isEmpty()){
                                for(int i=0;i<myWordFolderList.size();i++){
                                    myWordFolderRepository.delete(myWordFolderList.get(i));
                                }
                            }
                            userRepository.delete(userSelected);
                        return Header.OK();
                })
                .orElseThrow(()->new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER,"user"));
    }

    private UserApiResponse response(User user){
        System.out.println("USER IN RESPONSE : "+user);

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .weekAttendance(user.getWeekAttendance())
                .lastLoginAt(user.getLastLoginAt())
                .status(user.getStatus())
                .build();
        return userApiResponse;
    }

    private UserApiResponse responseForLogin(User user){
        System.out.println("USER IN RESPONSE : "+user);
        Integer learnFolder = 0;
        Integer knowFolder= 0;
        Integer dontKnowFolder = 0;
        List<MyWordFolder> myWordFolderList = myWordFolderRepository.findAllByUserId(user.getId());
        if(myWordFolderList.isEmpty()){
            throw new KoreaGuideException(KoreaGuideError.ENTITY_EMPTY_MYWORDFOLDER);
        }else{
            for(int i=0;i<myWordFolderList.size();i++){
                if(myWordFolderList.get(i).getFolderName().equals("1")){
                    learnFolder = myWordFolderList.get(i).getId();
                }
                else if(myWordFolderList.get(i).getFolderName().equals("2")){
                    knowFolder = myWordFolderList.get(i).getId();
                }
                else if(myWordFolderList.get(i).getFolderName().equals("3")){
                    dontKnowFolder = myWordFolderList.get(i).getId();
                }
            }
        }
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .weekAttendance(user.getWeekAttendance())
                .lastLoginAt(user.getLastLoginAt())
                .status(user.getStatus())
                .addFolderId(learnFolder)
                .completeFolderId(knowFolder)
                .learningFolderId(dontKnowFolder)
                .build();
        return userApiResponse;
    }

    private UserApiResponse response(User user,String token){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .lastLoginAt(user.getLastLoginAt())
                .weekAttendance(user.getWeekAttendance())
                .status(user.getStatus())
                .token(token)
                .build();
        System.out.println("RESPONSE: "+userApiResponse);
        return userApiResponse;
    }
    // 이메일 중복 검사
    public Header<UserApiResponse> checkDuplicateEmail(Header<UserApiRequest> request) {
        Optional<User> user = userRepository.findByEmail(request.getData().getEmail());
        if(user.isPresent()){
            throw new KoreaGuideException(KoreaGuideError.DUPLICATE_ERROR,"Email already exists");
        }else{
            return Header.OK("Email good to use");
        }
    }

    public Header<SessionResponseDto> authenticate(Header<SessionRequestDto> request){
        SessionRequestDto body = request.getData();
        String email = body.getEmail();
        String password = body.getPassword();
        // 해당 이메일을 가진 사용자가 존재하는지 여부
        User user=userRepository.findByEmail(email).orElseThrow(()->new EmailNotExistedException(email));

        // 해당 이메일 & 비번을 가진 사용자가 존재하는지 여부
        if(!passwordEncoder.matches(password,user.getPassword())){
//            throw new PasswordWrongException();
        }
        return null;
    }

    public UserApiResponse login(Header<SessionRequestDto> request) {
        User user = userRepository.findByEmail(request.getData().getEmail())
                .orElseThrow(()-> new KoreaGuideException(KoreaGuideError.ENTITY_NOT_FOUND_USER,"user"));
        System.out.println("USER IS: "+user);
        if (!passwordEncoder.matches(request.getData().getPassword(), user.getPassword())) {
            throw new KoreaGuideException(KoreaGuideError.WRONG_PASSWORD,"user");
        }
        System.out.println("USER id & nickname: "+user.getId()+"  "+user.getNickname());
        System.out.println("DAY OF WEEK : "+LocalDate.now().getDayOfWeek());

        String token = jwtUtil.createAccessToken(user.getId(),user.getNickname());
//        System.out.println("USER TOKEN: "+token);

        if(LocalDate.now().getDayOfWeek().compareTo(DayOfWeek.MONDAY)==0){
            System.out.println("NOW: "+LocalDate.now().getDayOfWeek());
            System.out.println("Mond: "+DayOfWeek.MONDAY);
            user.setWeekAttendance(1);
            user.setLastLoginAt(LocalDate.now());
            userRepository.save(user);
        }else {
            System.out.println("WHAT IS: " + user.getLastLoginAt());
            if (user.getWeekAttendance() == 0) {
                user.setLastLoginAt(LocalDate.now());
                user.setWeekAttendance(1);
                userRepository.save(user);
            }
            else{
                if (user.getLastLoginAt().compareTo(LocalDate.now()) != 0) {
//            System.out.println("LAST LOGIN: "+user.getLastLoginAt().toString());
//            System.out.println("NOW!!: "+LocalDate.now().toString());
                    Integer userWeekAttendance = user.getWeekAttendance();
                    user.setWeekAttendance(userWeekAttendance + 1);
                    user.setLastLoginAt(LocalDate.now());
                    userRepository.save(user);
                }
            }
        }



        return response(user,token);
//        return responseForLogin(user);
    }

}
