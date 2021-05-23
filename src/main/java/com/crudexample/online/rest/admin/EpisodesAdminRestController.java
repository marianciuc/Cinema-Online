package com.crudexample.online.rest.admin;

import com.crudexample.online.dto.EpisodeDto;
import com.crudexample.online.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/admin/episode/")
public class EpisodesAdminRestController {

    private final EpisodeService episodeService;

    @Autowired
    public EpisodesAdminRestController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @PostMapping("add/{id}")
    public ResponseEntity add(@Valid @RequestBody EpisodeDto episodeDto, @PathVariable Long id) {
        return episodeService.add(episodeDto, id);
    }

    @PostMapping("update/{id}")
    public ResponseEntity update(@Valid @RequestBody EpisodeDto episodeDto, @PathVariable Long id) {
        return episodeService.update(episodeDto, id);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return episodeService.delete(id);
    }

}
