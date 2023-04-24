package com.ad.reviewgate.mapper;

import com.ad.reviewgate.dto.ReviewPictureDTO;
import com.ad.reviewgate.model.ReviewPicture;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.ArrayUtils;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Optional;


@Component
public class ReviewPictureMapper extends MapperCommon<ReviewPicture, ReviewPictureDTO> {

    public ReviewPictureMapper() {
        super(ReviewPicture.class, ReviewPictureDTO.class);


    }

    final static Converter<String, Byte[]> DTO_TO_MODEL = mappingContext ->
            ArrayUtils.toObject(Optional.ofNullable(mappingContext.getSource())
                    .map(stringImage -> Base64.getDecoder().decode(stringImage))
                    .get());

    final static Converter<Byte[], String> MODEL_TO_DTO = mappingContext ->
            new String(Optional.ofNullable(mappingContext.getSource())
                    .map(byteArrayImage -> Base64.getEncoder().encode(ArrayUtils.toPrimitive(byteArrayImage)))
                    .get());


    @PostConstruct
    private void postConstruct() {

        getModelMapper().typeMap(ReviewPictureDTO.class, ReviewPicture.class).addMappings(mapper -> {
            mapper.using(DTO_TO_MODEL).<Byte[]>map(ReviewPictureDTO::getPicture, ReviewPicture::setPicture);
        });

        getModelMapper().typeMap(ReviewPicture.class, ReviewPictureDTO.class).addMappings(mapper -> {
            mapper.using(MODEL_TO_DTO).<String>map(ReviewPicture::getPicture, ReviewPictureDTO::setPicture);
        });

    }

}
