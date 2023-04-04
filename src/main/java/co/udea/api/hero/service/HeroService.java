package co.udea.api.hero.service;

import co.udea.api.hero.exception.BusinessException;
import co.udea.api.hero.model.Hero;
import co.udea.api.hero.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeroService {

    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }
    public Hero getHero(Integer id){
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if(!optionalHero.isPresent()){
            log.info("No se encuentra un heroe con ID: "+id);
            throw new BusinessException("El heroe no existe");
        }
        return optionalHero.get();
    }

    public Page<Hero> getHeroes(Pageable pageable) {
        return heroRepository.findAll(pageable);
    }
    public Page<Hero> searchHeroes(String term, Pageable pageable) {
        return heroRepository.findAllByNameContainingIgnoreCase(term, pageable);
    }



}
