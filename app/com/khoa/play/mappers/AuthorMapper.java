package com.khoa.play.mappers;

import com.khoa.play.jooq.tables.pojos.Author;
import com.khoa.play.models.AuthorDTO;

import javax.inject.Singleton;

@Singleton
public class AuthorMapper {

    public static AuthorDTO toDto(Author author) {
        try {
            AuthorDTO dto = new AuthorDTO.Builder()
                    .id(author.getId())
                    .firstName(author.getFirstName())
                    .lastName(author.getLastName())
                    .build();
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    public static Author toPojo(AuthorDTO dto) {
        return new Author(dto.getId(), dto.getFirstName(), dto.getLastName());
    }
}
