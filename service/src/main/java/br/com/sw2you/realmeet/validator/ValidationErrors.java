package br.com.sw2you.realmeet.validator;

import java.util.Iterator;
import java.util.List;
import lombok.Data;
import org.springframework.data.util.Streamable;

@Data
public class ValidationErrors implements Streamable<ValidationError> {
    private final List<ValidationError> validationErrorList;

    public ValidationErrors add(String field, String errorCode) {
        return add(new ValidationError(field, errorCode));
    }

    public ValidationErrors add(ValidationError validationError) {
        validationErrorList.add(validationError);
        return this;
    }

    public ValidationError getError(int index) {
        return validationErrorList.get(index);
    }

    public int getNumberOfErrors() {
        return validationErrorList.size();
    }

    public boolean hasErrors() {
        return !validationErrorList.isEmpty();
    }

    @Override
    public Iterator<ValidationError> iterator() {
        return validationErrorList.iterator();
    }
}