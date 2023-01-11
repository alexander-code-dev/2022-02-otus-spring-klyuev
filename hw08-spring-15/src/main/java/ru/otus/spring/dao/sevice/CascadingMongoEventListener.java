package ru.otus.spring.dao.sevice;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.otus.spring.dao.anotation.CascadeSave;
import ru.otus.spring.dao.anotation.DbSeqName;
import ru.otus.spring.dao.entity.AbstractDocument;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CascadingMongoEventListener extends AbstractMongoEventListener<Object> {

    MongoOperations mongoOperations;
    SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {

        ReflectionUtils.doWithFields(event.getSource().getClass(), field -> {
            ReflectionUtils.makeAccessible(field);

            // auto id
            if (field.isAnnotationPresent(DbSeqName.class)) {
                AbstractDocument abstractDocument = (AbstractDocument) event.getSource();
                if (Objects.isNull(abstractDocument.getId())) {
                    abstractDocument.setId(sequenceGeneratorService.getSeq((String) field.get(event.getSource())));
                }
            }

            // cascade save
            if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
                Object fieldValue = field.get(event.getSource());
                if (Objects.nonNull(fieldValue)) {
                    FieldCallback callback = new FieldCallback();
                    if (fieldValue instanceof Collection<?>) {
                        ((Collection<?>) fieldValue).forEach(mongoOperations::save);
                    } else {
                        ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
                        mongoOperations.save(fieldValue);
                    }
                }
            }
        });
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class FieldCallback implements ReflectionUtils.FieldCallback {
        boolean idFound;

        public void doWith(Field field) throws IllegalArgumentException {
            ReflectionUtils.makeAccessible(field);

            if (field.isAnnotationPresent(Id.class)) {
                idFound = true;
            }
        }

        public boolean isIdFound() {
            return idFound;
        }
    }
}
