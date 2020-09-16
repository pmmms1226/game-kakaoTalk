package game;

import game.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMissionUpdated_Alert(@Payload MissionUpdated missionUpdated){


        if(missionUpdated.isMe()){
            KakaoTalk kakaoTalk = new KakaoTalk();
            kakaoTalk.setMissionId(missionUpdated.getId());
            kakaoTalk.setStatus(missionUpdated.getStatus());

            System.out.println("##### listener Alert : " + missionUpdated.toJson());
        }
    }

}
