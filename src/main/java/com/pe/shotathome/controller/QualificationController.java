package com.ShopAtHome.Controller;

import com.ShopAtHome.Entity.Qualification;
import com.ShopAtHome.Excepcions.RecursoNoEncontrado;
import com.ShopAtHome.Repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QualificationController {
    @Autowired
    private BigtreeRepositorio bigtreeRepositorio;

    @Autowired
    private GreenleafRepositorio greenleafRepositorio;