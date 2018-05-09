package br.gov.rn.pm.sigp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.rn.pm.sigp.model.Posto;
import br.gov.rn.pm.sigp.service.PostoService;

@Controller
public class PostoController {

        @Autowired
        private PostoService service;

        @GetMapping("/posto")
        public ModelAndView findAll() {

            ModelAndView mv = new ModelAndView("posto/list");
            mv.addObject("postos", service.findAll());

            return mv;    
        }

        @GetMapping("/posto/add")
        public ModelAndView add(Posto posto) {
            ModelAndView mv = new ModelAndView("posto/add");
            mv.addObject("posto", posto);
            return mv;
        }

        @GetMapping("/posto/edit/{id}")
        public ModelAndView edit(@PathVariable("id") Long id) {

            return add(service.findOne(id));
        }

        @GetMapping("/posto/delete/{id}")
        public ModelAndView delete(@PathVariable("id") Long id) {

            service.delete(id);

            return findAll();
        }

        @PostMapping("/posto/save")
        public ModelAndView save(@Valid Posto posto, BindingResult result) {

            if(result.hasErrors()) {
                return add(posto);
            }

            service.save(posto);

            return findAll();
        }
}
