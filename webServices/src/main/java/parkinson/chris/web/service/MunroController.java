package parkinson.chris.web.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import parkinson.chris.model.MunroResult;
import parkinson.chris.model.MunroSearchCriteria;
import parkinson.chris.web.exception.MunroValidationException;

import java.util.List;

@RestController
public class MunroController {

    @PostMapping("/searchMunro")
    public List<MunroResult> searchMunro(@RequestBody MunroSearchCriteria searchCriteria){
        validateSearchCriteria(searchCriteria);
        
        return null;
    }

    private void validateSearchCriteria(MunroSearchCriteria searchCriteria) {
        try {
            validateHeight(searchCriteria.getMinimumHeight(), searchCriteria.getMaximumHeight());
            validateResultSize(searchCriteria.getMaximumResults());
        }catch (MunroValidationException validationException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "validation failed", validationException);
        }
    }

    private void validateResultSize(Integer maximumResults) {
        if(maximumResults != null && maximumResults < 1){
            throw new MunroValidationException("At least one result must be returned");
        }
    }

    private void validateHeight(Double minimumHeight, Double maximumHeight) {
        if(maximumHeight != null && maximumHeight < 1){
            throw new MunroValidationException("Maximum Height must be greater than zero");
        }

        if(minimumHeight != null && minimumHeight < 1){
            throw new MunroValidationException("Minimum Height must be greater than zero");
        }

        if(minimumHeight != null && maximumHeight != null){
            if(minimumHeight > maximumHeight){
                throw new MunroValidationException("Minimum Height must be less than Maximum Height");
            }
        }
    }
}
