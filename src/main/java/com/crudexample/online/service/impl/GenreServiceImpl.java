package com.crudexample.online.service.impl;

import com.crudexample.online.dto.GenreDto;
import com.crudexample.online.dto.MessageResponseDto;
import com.crudexample.online.model.Genre;
import com.crudexample.online.model.Status;
import com.crudexample.online.repository.GenreRepository;
import com.crudexample.online.service.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    public GenreServiceImpl(ModelMapper modelMapper, GenreRepository genreRepository) {
        this.modelMapper = modelMapper;
        this.genreRepository = genreRepository;
    }

    private ModelMapper modelMapper;
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        genreRepository.findAll();
        return null;
    }

    @Override
    public ResponseEntity create(GenreDto genreDto) {
        Optional<Genre> optional = Optional.ofNullable(genreRepository.findOneByName(genreDto.getName()));

        if (optional.isPresent()) {
            MessageResponseDto messageResponseDto = new MessageResponseDto();
            messageResponseDto.setMessage("Genre already exists");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(messageResponseDto);
        }

        Genre newGenre = modelMapper.map(genreDto, Genre.class);

        newGenre.setCreated(new Date());
        newGenre.setUpdated(new Date());
        newGenre.setStatus(Status.ACTIVE);

        return ResponseEntity.status(HttpStatus.OK).body(genreRepository.save(newGenre));
    }

    @Override
    public ResponseEntity update(GenreDto genreDto, Long id) {
        Optional<Genre> toUpdate = genreRepository.findById(id);

        if (toUpdate.isPresent() && genreDto.getName().length() > 2) {
            toUpdate.orElse(null).setName(genreDto.getName());

            return ResponseEntity.status(HttpStatus.OK).body(genreRepository.save(toUpdate.get()));
        }
        MessageResponseDto messageResponseDto = new MessageResponseDto();
        messageResponseDto.setMessage("Genre already exists");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(messageResponseDto);
    }
}
