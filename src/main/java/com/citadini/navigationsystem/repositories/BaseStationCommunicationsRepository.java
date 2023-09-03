package com.citadini.navigationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citadini.navigationsystem.domain.BaseStationCommunication;
import com.citadini.navigationsystem.domain.BaseStationCommunicationPK;

public interface BaseStationCommunicationsRepository extends JpaRepository<BaseStationCommunication, BaseStationCommunicationPK> {
}
