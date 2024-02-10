package com.illiapinchuk.testtask.persistence.repository;

import com.illiapinchuk.testtask.persistence.entity.AuctionPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionPhotoRepository extends JpaRepository<AuctionPhoto, Long> {}
