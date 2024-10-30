package hackaton.swing.domain.user.service;

import hackaton.swing.domain.user.passenger.Passenger;
import hackaton.swing.domain.user.passenger.repository.PassengerRepository;
import hackaton.swing.domain.user.presentation.dto.GetDto;
import hackaton.swing.domain.user.presentation.dto.UpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static hackaton.swing.domain.user.types.Keywords.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PassengerService {
    private final PassengerRepository passengerRepository;

    @Transactional
    public GetDto.KeywordsResponse getKeywordsP(String id) throws Exception {
        log.info("승객 찾기 : {}", id);
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new Exception("CANNOT FOUND PASSENGER"));

        Map<Integer, String> passengerKeywords = passenger.getPassengerKeywords();

        log.info("JSON 형식을 List<Integer> 형식으로 변환");
        List<Integer> keywords = new ArrayList<>(passengerKeywords.keySet());

        log.info("완료! Response 반환");
        return GetDto.KeywordsResponse.builder()
                .keywords(keywords)
                .build();
    }

    @Transactional
    public UpdateDto.Response updateKeywordsP(UpdateDto.KeywordsRequest request) throws Exception {
        log.info("데이터 유효성 검사");

        List<Integer> keywords = request.getKeywords();
        if (keywords.isEmpty()) throw new Exception("EMPTY REQUEST");

        log.info("List<Integer> 형식을 JSON 형식으로 변환");
        Map<Integer, String> passengerKeywords = keywords.stream()
                .flatMap(keyword -> Arrays.stream(values())
                        .filter(k -> k.getKey() == keyword)
                        .map(k -> new AbstractMap.SimpleEntry<>(k.getKey(), k.getValue()))
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        String id = "test";
        log.info("승객 찾기 : {}", id);
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new Exception("CANNOT FOUND PASSENGER"));

        log.info("수정된 사항 저장");
        passenger.setPassengerKeywords(passengerKeywords);
        passengerRepository.save(passenger);

        log.info("완료! Response 반환");
        return UpdateDto.Response.builder()
                .message("수정된 선호 키워드를 정상적으로 저장하였습니다.")
                .build();
    }
}
