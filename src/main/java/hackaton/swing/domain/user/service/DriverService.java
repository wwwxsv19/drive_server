package hackaton.swing.domain.user.service;

import hackaton.swing.domain.user.driver.repository.Driver;
import hackaton.swing.domain.user.driver.repository.repository.DriverRepository;
import hackaton.swing.domain.user.passenger.Passenger;
import hackaton.swing.domain.user.presentation.dto.GetDto;
import hackaton.swing.domain.user.presentation.dto.UpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static hackaton.swing.domain.user.types.Keywords.values;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DriverService {
    private final DriverRepository driverRepository;

    @Transactional
    public GetDto.KeywordsResponse getKeywordsD(String id) throws Exception {
        log.info("기사 찾기 : {}", id);
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new Exception("CANNOT FOUND DRIVER"));

        Map<Integer, String> driverKeywords = driver.getDriverKeywords();

        log.info("JSON 형식을 List<Integer> 형식으로 변환");
        List<Integer> keywords = new ArrayList<>(driverKeywords.keySet());

        log.info("완료! Response 반환");
        return GetDto.KeywordsResponse.builder()
                .keywords(keywords)
                .build();
    }

    @Transactional
    public UpdateDto.Response updateKeywordsD(UpdateDto.KeywordsRequest request) throws Exception {
        log.info("데이터 유효성 검사");

        List<Integer> keywords = request.getKeywords();
        if (keywords.isEmpty()) throw new Exception("EMPTY REQUEST");

        log.info("List<Integer> 형식을 JSON 형식으로 변환");
        Map<Integer, String> driverKeywords = keywords.stream()
                .flatMap(keyword -> Arrays.stream(values())
                        .filter(k -> k.getKey() == keyword)
                        .map(k -> new AbstractMap.SimpleEntry<>(k.getKey(), k.getValue()))
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        String id = "ttest";
        log.info("기사 찾기 : {}", id);
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new Exception("CANNOT FOUND DRIVER"));

        log.info("수정된 사항 저장");
        driver.setDriverKeywords(driverKeywords);
        driverRepository.save(driver);

        log.info("완료! Response 반환");
        return UpdateDto.Response.builder()
                .message("수정된 선호 키워드를 정상적으로 저장하였습니다.")
                .build();
    }

}
