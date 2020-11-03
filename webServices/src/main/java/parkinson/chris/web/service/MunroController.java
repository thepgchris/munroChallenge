package parkinson.chris.web.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import parkinson.chris.model.MunroResult;
import parkinson.chris.model.MunroSearchCriteria;

import java.util.List;

@RestController
public class MunroController {

    @PostMapping("/searchMunro")
    public List<MunroResult> searchMunro(@RequestBody MunroSearchCriteria searchCriteria){
        return null;
    }
}
