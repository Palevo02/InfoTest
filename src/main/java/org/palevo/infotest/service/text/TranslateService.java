package org.palevo.infotest.service.text;

import org.palevo.infotest.DTO.ProcessCreateDTO;
import org.palevo.infotest.model.reverso.Options;
import org.palevo.infotest.model.reverso.ReversoResponse;
import org.palevo.infotest.model.reverso.TranslateReversoRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TranslateService {
    private final String TOKEN = "apify_api_P8g5BcE5lBTobxByuB64igGc6VMxW33NodcE";
    private final String URL = "https://api.reverso.net/translate/v1/translation";

    public String fakeTranslate(ProcessCreateDTO processCreateDTO){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Когда нибудь тут будет переводчик";
    }
    public String translateOld(ProcessCreateDTO processCreateDTO) {
        Options options = Options.builder()
                .contextResults(true)
                .languageDetection(true)
                .origin("translation.web")
                .sentenceSplitter(true)
                .build();

        TranslateReversoRequest translate = TranslateReversoRequest.builder()
                .format("text")
                .from(processCreateDTO.getFromLanguage())
                .to(processCreateDTO.getToLanguage())
                .input(processCreateDTO.getValue())
                .options(options)
                .build();
        return requestReverso(translate);
    }

    public String requestReverso(TranslateReversoRequest translate) {
        RestTemplate restTemplate = new RestTemplate();
        ReversoResponse response = restTemplate.postForObject(URL,
                translate, ReversoResponse.class);
        return response.getTranslation().get(0);
    }
}
