package com.khoa.play.services;

import com.khoa.play.jooq.tables.pojos.Author;
import com.khoa.play.mappers.AuthorMapper;
import com.khoa.play.models.AuthorDTO;
import com.khoa.play.repositories.AuthorRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorService {

    private AuthorRepository authorRepository;

    @Inject
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getListAuthor() {
        List<Author> authorList = authorRepository.getListAuthor();
        return authorList.stream().map(AuthorMapper::toDto).collect(Collectors.toList());
    }

    public AuthorDTO getAuthor(Long id) {
        return null;
    }

    public AuthorDTO insertAuthor(AuthorDTO dto) {
        Author author = AuthorMapper.toPojo(dto);
        return AuthorMapper.toDto(authorRepository.createAuthor(author));
    }

    public AuthorDTO updateAuthor(AuthorDTO author) {
        Author pojo = AuthorMapper.toPojo(author);
        return AuthorMapper.toDto(authorRepository.updateAuthor(pojo));
    }

    public boolean deleteAuthor(Integer id) {
        return authorRepository.deleteAuthor(id);
    }
}
