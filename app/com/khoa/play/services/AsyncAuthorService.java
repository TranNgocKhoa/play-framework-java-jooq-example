package com.khoa.play.services;

import com.khoa.play.jooq.tables.pojos.Author;
import com.khoa.play.mappers.AuthorMapper;
import com.khoa.play.models.AuthorDTO;
import com.khoa.play.repositories.AsyncAuthorRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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

    public CompletionStage<AuthorDTO> insertAuthor(AuthorDTO authorDTO) {
        CompletionStage<AuthorDTO> authorCompletionStage = this.authorRepository.insertAuthor(AuthorMapper.toPojo(authorDTO))
                .thenApply(record -> new Author(record.getId(), record.getFirstName(), record.getLastName(),
                                record.getDateOfBirth(), record.getYearOfBirth(), record.getDistinguished())
                )
                .thenApply(AuthorMapper::toDto);
        return authorCompletionStage;
    }

    public CompletionStage<Void> insertAuthorTx(AuthorDTO authorDTO) {
        return  CompletableFuture.runAsync(() -> this.authorRepository.insertAuthorTransaction(AuthorMapper.toPojo(authorDTO)));
    }

    public CompletionStage<List<AuthorDTO>> getListAuthor2() {
        return this.authorRepository.getListAuthor2()
                .thenApply(authors ->
                        authors.stream()
                                .map(AuthorMapper::toDto)
                                .collect(Collectors.toList()));
    }
}
