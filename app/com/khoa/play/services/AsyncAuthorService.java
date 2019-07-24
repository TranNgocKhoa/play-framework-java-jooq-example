package com.khoa.play.services;

import com.khoa.play.mappers.AuthorMapper;
import com.khoa.play.models.AuthorDTO;
import com.khoa.play.repositories.AsyncAuthorRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class AsyncAuthorService {
    private AsyncAuthorRepository authorRepository;

    @Inject
    public AsyncAuthorService(AsyncAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public CompletionStage<List<AuthorDTO>> getListAuthor() {
        return this.authorRepository.getListAuthor()
                .thenApply(authors ->
                        authors.stream()
                                .map(AuthorMapper::toDto)
                                .collect(Collectors.toList()));
    }
}
